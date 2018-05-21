package com.bulain.activiti.dao;

import com.bulain.activiti.model.OrderMaster;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.common.dao.PagedMapper;

public interface OrderMasterMapper extends PagedMapper<OrderMaster, OrderSearch> {
    OrderMaster selectByName(String name);
}