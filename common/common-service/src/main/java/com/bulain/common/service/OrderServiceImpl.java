package com.bulain.common.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.OrderMapper;
import com.bulain.common.model.Order;
import com.bulain.common.pojo.OrderSearch;

public class OrderServiceImpl extends PagedServiceImpl<Order, OrderSearch> implements OrderService {
    private OrderMapper orderMapper;

    @Override
    protected PagedMapper<Order, OrderSearch> getPagedMapper() {
        return orderMapper;
    }

    public Order getByWfId(String wfId) {
        return orderMapper.selectByWfId(wfId);
    }

    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }
}
