package com.bulain.hibernate.core;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.page.Search;

public interface PagedService<T, S extends Search> extends BasicService<T> {
    List<T> find(DetachedCriteria dc, OrderBy orderBy);
    Long count(DetachedCriteria dc);
    List<T> page(DetachedCriteria dc, Page page, OrderBy orderBy);
    
    List<T> find(S search);
    Long count(S search);
    List<T> page(S search, Page page);
}
