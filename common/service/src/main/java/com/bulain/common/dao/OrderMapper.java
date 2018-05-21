package com.bulain.common.dao;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.Order;
import com.bulain.common.pojo.OrderSearch;

public interface OrderMapper extends PagedMapper<Order, OrderSearch> {
    Order selectByWfId(String wfId);
}