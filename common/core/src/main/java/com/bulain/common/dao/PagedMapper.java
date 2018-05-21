package com.bulain.common.dao;

import java.util.List;

import com.bulain.common.page.Search;

public interface PagedMapper<T, S extends Search> extends BasicMapper<T> {
    List<T> find(S search);
    Long count(S search);
    List<T> page(S search);
}
