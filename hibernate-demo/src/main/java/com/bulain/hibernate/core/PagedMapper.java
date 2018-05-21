package com.bulain.hibernate.core;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;

public interface PagedMapper<T, S extends Search> extends BasicMapper<T> {
    List<T> find(DetachedCriteria dc, OrderBy orderBy);
    Long count(DetachedCriteria dc);
    List<T> page(DetachedCriteria dc, Page page, OrderBy orderBy);
    
    List<T> find(String queryName, S search);
    Long count(String queryName, S search);
    List<T> page(String queryName, S search);
}
