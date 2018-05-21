package com.bulain.activiti.service;

import java.util.List;

import com.bulain.activiti.model.Referance;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ReferanceBean;
import com.bulain.activiti.pojo.ReferanceSearch;
import com.bulain.common.service.PagedService;

public interface ReferanceService extends PagedService<Referance, ReferanceSearch> {
    void insert(ReferanceBean referanceBean);

    String getText(String name, String code, String lang);
    String getText(String name, String code, String lang, String category);

    List<Item> findItem(String name, String lang);
    List<Item> findItem(String name, String lang, String category);
}
