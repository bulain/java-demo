package com.bulain.common.service;

import com.bulain.common.service.PagedService;
import com.bulain.common.model.Order;
import com.bulain.common.pojo.OrderSearch;

public interface OrderService extends PagedService<Order, OrderSearch> {
    Order getByWfId(String wfId);
}
