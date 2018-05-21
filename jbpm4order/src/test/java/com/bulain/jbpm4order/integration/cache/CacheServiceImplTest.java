package com.bulain.jbpm4order.integration.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.cache.CacheService;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;

public class CacheServiceImplTest extends ServiceTestCase {
    @Autowired
    private CacheService cacheService;

    @Test
    public void testCacheId() {
        Page page = new Page();
        page.setPage(10);
        page.setCount(100);

        boolean set = cacheService.set(Page.class, Long.valueOf(page.getPage()), page);
        assertTrue(set);

        Page get = (Page) cacheService.get(Page.class, Long.valueOf(page.getPage()));
        assertNotNull(get);
        assertEquals(page.getLow(), get.getLow());
        assertEquals(page.getHigh(), get.getHigh());

        page = new Page();
        page.setPage(5);
        page.setCount(100);

        boolean add = cacheService.add(Page.class, Long.valueOf(page.getPage()), page);
        assertTrue(add);

        get = (Page) cacheService.get(Page.class, Long.valueOf(page.getPage()));
        assertNotNull(get);
        assertEquals(page.getLow(), get.getLow());
        assertEquals(page.getHigh(), get.getHigh());

        page = new Page();
        page.setPage(5);
        page.setCount(1000);

        boolean replace = cacheService.replace(Page.class, Long.valueOf(page.getPage()), page);
        assertTrue(replace);

        get = (Page) cacheService.get(Page.class, Long.valueOf(page.getPage()));
        assertNotNull(get);
        assertEquals(page.getLow(), get.getLow());
        assertEquals(page.getHigh(), get.getHigh());

        boolean delete = cacheService.delete(Page.class, Long.valueOf(page.getPage()));
        assertTrue(delete);
    }

    @Test
    public void testCacheKey() {
        Page page = new Page();
        page.setPage(10);
        page.setCount(100);

        boolean set = cacheService.set(Page.class, Long.toString(page.getPage()), page);
        assertTrue(set);

        Page get = (Page) cacheService.get(Page.class, Long.toString(page.getPage()));
        assertNotNull(get);
        assertEquals(page.getLow(), get.getLow());
        assertEquals(page.getHigh(), get.getHigh());

        page = new Page();
        page.setPage(5);
        page.setCount(100);

        boolean add = cacheService.add(Page.class, Long.toString(page.getPage()), page);
        assertTrue(add);

        get = (Page) cacheService.get(Page.class, Long.toString(page.getPage()));
        assertNotNull(get);
        assertEquals(page.getLow(), get.getLow());
        assertEquals(page.getHigh(), get.getHigh());

        page = new Page();
        page.setPage(5);
        page.setCount(1000);

        boolean replace = cacheService.replace(Page.class, Long.toString(page.getPage()), page);
        assertTrue(replace);

        get = (Page) cacheService.get(Page.class, Long.toString(page.getPage()));
        assertNotNull(get);
        assertEquals(page.getLow(), get.getLow());
        assertEquals(page.getHigh(), get.getHigh());

        boolean delete = cacheService.delete(Page.class, Long.toString(page.getPage()));
        assertTrue(delete);

    }
}
