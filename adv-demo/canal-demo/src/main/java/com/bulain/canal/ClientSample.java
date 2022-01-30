package com.bulain.canal;

import java.net.InetSocketAddress;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.common.utils.AddressUtils;
import com.alibaba.otter.canal.protocol.CanalEntry.Column;
import com.alibaba.otter.canal.protocol.CanalEntry.Entry;
import com.alibaba.otter.canal.protocol.CanalEntry.EntryType;
import com.alibaba.otter.canal.protocol.CanalEntry.EventType;
import com.alibaba.otter.canal.protocol.CanalEntry.RowChange;
import com.alibaba.otter.canal.protocol.CanalEntry.RowData;
import com.alibaba.otter.canal.protocol.Message;

public class ClientSample {

    public static void main(String args[]) {

        // 创建链接    
        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(AddressUtils.getHostIp(),
                11111), "example", "", "");
        int batchSize = 1000;
        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            while (true) {
                Message message = connector.getWithoutAck(batchSize); // 获取指定数量的数据    
                long batchId = message.getId();
                int size = message.getEntries().size();
                if (batchId == -1 || size == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    printEntry(message.getEntries());
                }

                connector.ack(batchId); // 提交确认    
            }

        } finally {
            connector.disconnect();
        }
    }

    private static void printEntry(List<Entry> entrys) {
        for (Entry entry : entrys) {
            if (entry.getEntryType() == EntryType.TRANSACTIONBEGIN || entry.getEntryType() == EntryType.TRANSACTIONEND) {
                continue;
            }

            RowChange rowChage = null;
            try {
                rowChage = RowChange.parseFrom(entry.getStoreValue());
            } catch (Exception e) {
                throw new RuntimeException("ERROR ## parser of eromanga-event has an error , data:" + entry.toString(),
                        e);
            }

            String schemaName = entry.getHeader().getSchemaName();
            String tableName = entry.getHeader().getTableName();

            EventType eventType = rowChage.getEventType();
            System.out.println(String.format("================> binlog[%s:%s] , name[%s,%s] , eventType : %s", entry
                    .getHeader().getLogfileName(), entry.getHeader().getLogfileOffset(), entry.getHeader()
                    .getSchemaName(), entry.getHeader().getTableName(), eventType));

            for (RowData rowData : rowChage.getRowDatasList()) {
                if (eventType == EventType.DELETE) {
                    redisDelete(schemaName, tableName, rowData.getBeforeColumnsList());
                } else if (eventType == EventType.INSERT) {
                    redisInsert(schemaName, tableName, rowData.getAfterColumnsList());
                } else {
                    redisUpdate(schemaName, tableName, rowData.getAfterColumnsList());
                }
            }
        }
    }

    private static void redisInsert(String schemaName, String tableName, List<Column> columns) {
        JSONObject json = new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            RedisUtil.stringSet(String.format("%s/%s/%s", schemaName, tableName, columns.get(0).getValue()),
                    json.toJSONString());
        }
    }

    private static void redisUpdate(String schemaName, String tableName, List<Column> columns) {
        JSONObject json = new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            RedisUtil.stringSet(String.format("%s/%s/%s", schemaName, tableName, columns.get(0).getValue()),
                    json.toJSONString());
        }
    }

    private static void redisDelete(String schemaName, String tableName, List<Column> columns) {
        JSONObject json = new JSONObject();
        for (Column column : columns) {
            json.put(column.getName(), column.getValue());
        }
        if (columns.size() > 0) {
            RedisUtil.delKey(String.format("%s/%s/%s", schemaName, tableName, columns.get(0).getValue()));
        }
    }

}
