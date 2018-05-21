package com.bulain.activiti.service;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.bulain.activiti.dao.OrderHisMapper;
import com.bulain.activiti.dao.OrderMapper;
import com.bulain.activiti.dao.OrderMasterMapper;
import com.bulain.activiti.model.Order;
import com.bulain.activiti.model.OrderHis;
import com.bulain.activiti.model.OrderMaster;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;

public class OrderServiceImpl extends PagedServiceImpl<Order, OrderSearch> implements OrderService {
    private OrderMapper orderMapper;
    private OrderMasterMapper orderMasterMapper;
    private OrderHisMapper orderHisMapper;

    public Order getByWfId(String wfId) {
        return orderMapper.getByWfId(wfId);
    }

    @Override
    public PagedMapper<Order, OrderSearch> getPagedMapper() {
        return orderMapper;
    }

    public void finish(Long id) {
        Order order = orderMapper.selectByPrimaryKey(id);

        Date effDate = new Date();

        // save order master
        boolean found = true;
        OrderMaster orderMaster = orderMasterMapper.selectByName(order.getName());
        if (orderMaster == null) {
            orderMaster = new OrderMaster();
            found = false;
        }
        BeanUtils.copyProperties(order, orderMaster);
        orderMaster.setEffDate(effDate);
        if (found) {
            orderMasterMapper.updateByPrimaryKey(orderMaster);
        } else {
            orderMasterMapper.insert(orderMaster);
        }

        // insert order history
        OrderHis orderHis = new OrderHis();
        BeanUtils.copyProperties(order, orderHis);
        orderHis.setEffDate(effDate);
        orderHisMapper.insert(orderHis);

        // delete order
        orderMapper.deleteByPrimaryKey(id);
    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public void setOrderMasterMapper(OrderMasterMapper orderMasterMapper) {
        this.orderMasterMapper = orderMasterMapper;
    }

    public void setOrderHisMapper(OrderHisMapper orderHisMapper) {
        this.orderHisMapper = orderHisMapper;
    }

}
