package com.bulain.hibernate.core;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;

public abstract class PagedServiceImpl<T, S extends Search> extends BasicServiceImpl<T> implements PagedService<T, S> {
    private static final String DYNAMIC_FIND = "dynamicFind";

    protected abstract PagedMapper<T, S> getPagedMapper();
    
    protected BasicMapper<T> getBasicMapper() {
        return getPagedMapper();
    }
    
    public List<T> find(DetachedCriteria dc, OrderBy orderBy) {
        return getPagedMapper().find(dc, orderBy);
    }
    
    public Long count(DetachedCriteria dc) {
        return getPagedMapper().count(dc);
    }
    
    public List<T> page(DetachedCriteria dc, Page page, OrderBy orderBy) {
        // set pagination
        Long cnt = getPagedMapper().count(dc);
        page.setCount(cnt);
        return getPagedMapper().page(dc, page, orderBy);
    }
    
    public List<T> find(S search) {
        List<T> list = getPagedMapper().find(DYNAMIC_FIND, search);
        return list;
    }
    
    public Long count(S search) {
        Long cnt = getPagedMapper().count(DYNAMIC_FIND, search);
        return cnt;
    }
    
    public List<T> page(S search, Page page) {
        Long cnt = getPagedMapper().count(DYNAMIC_FIND, search);
        page.setCount(cnt.longValue());
        search.setLow(page.getLow());
        search.setHigh(page.getHigh());
        List<T> list = getPagedMapper().find(DYNAMIC_FIND, search);
        return list;
    }
}
