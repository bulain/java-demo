package com.bulain.activiti.service;

import com.bulain.activiti.model.Order;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.common.service.PagedService;

public interface OrderService extends PagedService<Order, OrderSearch> {
    Order getByWfId(String wfId);

    void finish(Long id);
}
