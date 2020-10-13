package com.bulain.common.service;

import java.util.List;

import com.bulain.common.service.PagedService;
import com.bulain.common.model.Reference;
import com.bulain.common.model.ReferenceBean;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ReferenceSearch;

public interface ReferenceService extends PagedService<Reference, ReferenceSearch> {
    void insert(ReferenceBean referenceBean);

    String getText(String name, String code, String lang);
    String getText(String name, String code, String lang, String catagory);
    
    Item getItem(String name, String code, String lang);
    Item getItem(String name, String code, String lang, String catagory);

    List<Item> findItem(String name, String lang);
    List<Item> findItem(String name, String lang, String catagory);
}
