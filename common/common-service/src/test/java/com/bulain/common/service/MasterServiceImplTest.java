package com.bulain.common.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.pojo.Master;

@DataSet(file = "test-data/init_masters.xml")
public class MasterServiceImplTest extends ServiceTestCase {
    @Autowired
    private MasterService masterService;

    @Test
    public void testGetValue4Group() {
        String value4Group = masterService.getValue4Group(Long.valueOf(105));
        assertEquals("name_page", value4Group);
    }

    @Test
    public void testFindMaster4Group() {
        List<Master> findMaster4Group = masterService.findMaster4Group();
        assertEquals(4, findMaster4Group.size());
    }

    @Test
    public void testGetValue4Person() {
        String value4Person = masterService.getValue4Person(Long.valueOf(105));
        assertEquals("last_name_page, first_name_page", value4Person);
    }

    @Test
    public void testFindMaster4Person() {
        List<Master> findMaster4Person = masterService.findMaster4Person();
        assertEquals(4, findMaster4Person.size());
    }

}
