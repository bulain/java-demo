package com.bulain.cache;

import com.bulain.cache.model.Demo;
import com.bulain.cache.service.DemoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml",
        "classpath*:spring/springContext-redis.xml"})
public class RedisCacheDemo {

    @Autowired
    private DemoService demoService;

    @Test
    public void testCache() {
        Long id = 1L;
        Demo req = null;
        Demo resp = null;

        //1
        resp = demoService.get(id);
        assertNull(resp);

        //2
        req = new Demo();
        req.setTitle("title-1");
        req.setDescr("descr-1");
        Long pk = demoService.insert(req, true);
        assertEquals(id, pk);

        //3
        resp = demoService.get(id);
        assertNotNull(resp);
        assertEquals("title-1", resp.getTitle());

        //4
        resp = demoService.get(id);
        assertNotNull(resp);
        assertEquals("title-1", resp.getTitle());

        //5
        req = new Demo();
        req.setId(id);
        req.setTitle("title-1-u");
        req.setDescr("descr-1-u");
        pk = demoService.update(req, true);
        assertEquals(id, pk);

        //6
        resp = demoService.get(id);
        assertNotNull(resp);
        assertEquals("title-1-u", resp.getTitle());

        //7
        demoService.get(id);
        resp = demoService.get(id);
        assertNotNull(resp);
        assertEquals("title-1-u", resp.getTitle());

        //8
        demoService.delete(id);

        //9
        resp = demoService.get(id);
        assertNull(resp);

        //10
        resp = demoService.get(id);
        assertNull(resp);

    }

}
