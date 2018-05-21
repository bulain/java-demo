package com.bulain.common.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractCacheServiceImpl implements CacheService {
    private static final String COMAID = "_id_";
    private static final String COMAKEY = "_key_";

    protected String parseCacheKey(Class<?> clz, Long id) {
        return String.format("%s%s%d", clz.getName(), COMAID, id);
    }
    protected String parseCacheKey(Class<?> clz, String key) {
        return String.format("%s%s%s", clz.getName(), COMAKEY, key);
    }
    protected Collection<String> parseCacheKeyById(Class<?> clz, Collection<Long> ids) {
        List<String> list = new ArrayList<String>();
        for (Long id : ids) {
            list.add(String.format("%s%s%d", clz.getName(), COMAID, id));
        }
        return list;
    }
    protected Collection<String> parseCacheKeyByKey(Class<?> clz, Collection<String> keys) {
        List<String> list = new ArrayList<String>();
        for (String key : keys) {
            list.add(String.format("%s%s%s", clz.getName(), COMAKEY, key));
        }
        return list;
    }
}
