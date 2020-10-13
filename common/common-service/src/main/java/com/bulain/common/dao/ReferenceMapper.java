package com.bulain.common.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.Reference;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ReferenceSearch;

public interface ReferenceMapper extends PagedMapper<Reference, ReferenceSearch> {
    List<Item> selectListByExample(ReferenceSearch search);
    Item selectItemByExample(ReferenceSearch search);
}