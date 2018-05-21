package com.bulain.common.service;

import java.util.List;

import com.bulain.common.dao.BasicMapper;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;

public abstract class PagedServiceImpl<T, S extends Search> extends BasicServiceImpl<T> implements PagedService<T, S> {
    protected abstract PagedMapper<T, S> getPagedMapper();

    protected BasicMapper<T> getBasicMapper() {
        return getPagedMapper();
    }

    public List<T> find(S search) {
        return getPagedMapper().find(search);
    }

    public Long count(S search) {
        return getPagedMapper().count(search);
    }

    public List<T> page(S search, Page page) {
        Long count = getPagedMapper().count(search);
        page.setCount(count.longValue());
        search.setLow(page.getLow());
        search.setHigh(page.getHigh());
        return getPagedMapper().page(search);
    }
}
