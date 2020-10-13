package com.bulain.common.service;

import com.bulain.common.service.PagedService;
import com.bulain.common.model.Content;
import com.bulain.common.pojo.ContentSearch;

public interface ContentService extends PagedService<Content, ContentSearch> {
    Content getWithoutBLOBs(Long id);
}
