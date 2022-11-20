package com.bulain.influxdb;

import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.junit.jupiter.api.Test;

public class BasicDemo {

    @Test
    public void testBatchPoints() {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.1.48:8086");
        String dbName = "tsdemo";
        influxDB.createDatabase(dbName);

        BatchPoints batchPoints = BatchPoints.database(dbName).tag("async", "true").retentionPolicy("autogen")
                .consistency(ConsistencyLevel.ALL).build();
        Point point1 = Point.measurement("cpu").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("idle", 90L).addField("user", 9L).addField("system", 1L).build();
        Point point2 = Point.measurement("disk").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("used", 80L).addField("free", 1L).build();
        batchPoints.point(point1);
        batchPoints.point(point2);
        influxDB.write(batchPoints);
        Query query = new Query("SELECT idle FROM cpu", dbName);
        influxDB.query(query);
        influxDB.deleteDatabase(dbName);
    }

    @Test
    public void testEnableBatch() {
        InfluxDB influxDB = InfluxDBFactory.connect("http://192.168.1.48:8086");
        String dbName = "tsdemo";
        influxDB.createDatabase(dbName);

        // Flush every 2000 Points, at least every 100ms
        influxDB.enableBatch(2000, 100, TimeUnit.MILLISECONDS);

        Point point1 = Point.measurement("cpu").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("idle", 90L).addField("user", 9L).addField("system", 1L).build();
        Point point2 = Point.measurement("disk").time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .addField("used", 80L).addField("free", 1L).build();

        influxDB.write(dbName, "autogen", point1);
        influxDB.write(dbName, "autogen", point2);
        Query query = new Query("SELECT idle FROM cpu", dbName);
        influxDB.query(query);
        influxDB.deleteDatabase(dbName);
    }

}
