package com.bulain.activiti.dao;

import com.bulain.activiti.model.Order;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.common.dao.PagedMapper;

public interface OrderMapper extends PagedMapper<Order, OrderSearch> {
    Order getByWfId(String wfId);
}