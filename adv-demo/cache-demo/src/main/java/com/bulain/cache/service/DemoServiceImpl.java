package com.bulain.cache.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import com.bulain.cache.model.Demo;

public class DemoServiceImpl implements DemoService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Long pk = 0L;
    private Map<Long, Demo> map = new HashMap<>();

    @Cacheable(value = "DemoService", key = "#id")
    public Demo get(Long id) {
        logger.debug("get({})", id);

        return map.get(id);
    }

    @CacheEvict(value = "DemoService", key = "#data.id")
    public Long insert(Demo data, boolean forced) {
        logger.debug("insert({})", data);

        data.setId(++pk);
        map.put(pk, data);
        return pk;
    }

    @CacheEvict(value = "DemoService", key = "#data.id")
    public Long update(Demo data, boolean forced) {
        logger.debug("update({},{})", data.getId(), data);

        Long id = data.getId();
        map.put(id, data);
        return id;
    }

    @CacheEvict(value = "DemoService", key = "#id")
    public void delete(Long id) {
        logger.debug("delete({})", id);

        map.remove(id);
    }

    @CacheEvict(value = "DemoService", key = "#data.id")
    public Long save(Demo data, boolean forced) {
        Long id = data.getId();
        if (id == null) {
            id = insert(data, forced);
        } else {
            id = update(data, forced);
        }
        return id;
    }

}
