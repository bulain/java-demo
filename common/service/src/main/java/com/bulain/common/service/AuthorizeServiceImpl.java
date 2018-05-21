package com.bulain.common.service;

import java.util.List;

import com.bulain.common.cache.CacheService;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.AuthorizeMapper;
import com.bulain.common.model.Authorize;
import com.bulain.common.pojo.AuthorizeSearch;

public class AuthorizeServiceImpl extends PagedServiceImpl<Authorize, AuthorizeSearch> implements AuthorizeService {
    private static final String DEFAULT_ACTION = "*";
    private static final String COMA = "__";

    private AuthorizeMapper authorizeMapper;
    private CacheService cacheService;

    @Override
    protected PagedMapper<Authorize, AuthorizeSearch> getPagedMapper() {
        return authorizeMapper;
    }

    @Override
    public void update(Authorize authorize, boolean forced) {
        super.update(authorize, forced);

        String key = authorize.getController() + COMA + authorize.getAction();
        cacheService.delete(Authorize.class, key);
    }

    public String getPermission(String controller, String action) {
        String key = controller + COMA + action;
        String permission = (String) cacheService.get(Authorize.class, key);

        AuthorizeSearch search = new AuthorizeSearch();
        search.setController(controller);
        search.setAction(action);
        List<Authorize> listAuthorize = authorizeMapper.find(search);

        if (listAuthorize.size() <= 0) {
            search.setAction(DEFAULT_ACTION);
            listAuthorize = authorizeMapper.find(search);
        }
        if (listAuthorize.size() > 0) {
            Authorize authorize = listAuthorize.get(0);
            permission = authorize.getPermission();
            cacheService.add(Authorize.class, key, permission);
        }

        return permission;
    }

    public void setAuthorizeMapper(AuthorizeMapper authorizeMapper) {
        this.authorizeMapper = authorizeMapper;
    }

    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }
}
