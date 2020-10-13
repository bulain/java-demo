package com.bulain.common.dao;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.Content;
import com.bulain.common.pojo.ContentSearch;

public interface ContentMapper extends PagedMapper<Content, ContentSearch> {
    int updateByPrimaryKeyWithBLOBs(Content record);
    Content selectByPrimaryKeyWithoutBLOBs(Long id);
}