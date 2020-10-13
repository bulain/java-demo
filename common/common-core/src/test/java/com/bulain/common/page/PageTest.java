package com.bulain.common.page;

import static org.junit.Assert.*;
import org.junit.Test;

public class PageTest {
    private Page page;
    
    @Test
    public void testPageSize10() {
        page = new Page();
        page.setPageSize(10);
        page.setPage(1);
        page.setCount(0);
        assertEquals(0, page.getLow());
        assertEquals(10, page.getHigh());
        assertEquals(1, page.getPage());
        assertEquals(10, page.getPageSize());
        
        page = new Page();
        page.setPageSize(10);
        page.setPage(3);
        page.setCount(0);
        assertEquals(0, page.getLow());
        assertEquals(10, page.getHigh());
        assertEquals(1, page.getPage());
        assertEquals(10, page.getPageSize());
        
        page = new Page();
        page.setPageSize(10);
        page.setPage(3);
        page.setCount(5);
        assertEquals(0, page.getLow());
        assertEquals(10, page.getHigh());
        assertEquals(1, page.getPage());
        assertEquals(10, page.getPageSize());
        
        page = new Page();
        page.setPageSize(10);
        page.setPage(1);
        page.setCount(40);
        assertEquals(0, page.getLow());
        assertEquals(10, page.getHigh());
        assertEquals(1, page.getPage());
        assertEquals(10, page.getPageSize());
        
        page = new Page();
        page.setPageSize(10);
        page.setPage(5);
        page.setCount(41);
        assertEquals(40, page.getLow());
        assertEquals(50, page.getHigh());
        assertEquals(5, page.getPage());
        assertEquals(10, page.getPageSize());
        
    }
}
