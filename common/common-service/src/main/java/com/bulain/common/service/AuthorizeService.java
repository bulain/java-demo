package com.bulain.common.service;

import com.bulain.common.service.PagedService;
import com.bulain.common.model.Authorize;
import com.bulain.common.pojo.AuthorizeSearch;

public interface AuthorizeService extends PagedService<Authorize, AuthorizeSearch> {
    String getPermission(String controller, String action);
}
