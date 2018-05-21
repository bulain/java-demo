package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.pojo.Master;

@DataSet(file = "test-data/init_masters.xml")
public class MasterMapperTest extends ServiceTestCase {
    @Autowired
    private MasterMapper masterMapper;

    @Test
    public void testSelectList4Group() {
        List<Master> selectList4Group = masterMapper.selectList4Group();
        assertEquals(3, selectList4Group.size());
    }

    @Test
    public void testSelectMaster4Group() {
        Master selectMaster4Group = masterMapper.selectMaster4Group(Long.valueOf(105));
        assertEquals(Long.valueOf(105), selectMaster4Group.getKey());
        assertEquals("name_page", selectMaster4Group.getValue());
    }

    @Test
    public void testSelectList4Person() {
        List<Master> selectList4Person = masterMapper.selectList4Person();
        assertEquals(3, selectList4Person.size());
    }

    @Test
    public void testSelectMaster4Person() {
        Master selectMaster4Person = masterMapper.selectMaster4Person(Long.valueOf(105));
        assertNotNull(selectMaster4Person);
        assertEquals(Long.valueOf(105), selectMaster4Person.getKey());
        assertEquals("last_name_page, first_name_page", selectMaster4Person.getValue());
    }

}
