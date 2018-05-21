package com.bulain.activiti.dao;

import java.util.List;

import com.bulain.activiti.model.Referance;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ReferanceSearch;
import com.bulain.common.dao.PagedMapper;

public interface ReferanceMapper extends PagedMapper<Referance, ReferanceSearch> {
    List<Item> selectListByExample(ReferanceSearch search);
    Item selectItemByExample(ReferanceSearch search);
}