package com.bulain.activiti.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.activiti.model.Group;
import com.bulain.activiti.pojo.GroupSearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.DaoTestCase;

@DataSet(file = "test-data/init_groups.xml")
public class GroupMapperTest extends DaoTestCase {
    @Autowired
    private GroupMapper groupMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = groupMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Group record = new Group();
        record.setName("name");
        record.setType("type");
        int insert = groupMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Group record = new Group();
        record.setName("name");
        record.setType("type");
        int insert = groupMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Group selectByPrimaryKey = groupMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);

        assertEquals("name_102", selectByPrimaryKey.getName());
        assertEquals("type_102", selectByPrimaryKey.getType());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Group record = new Group();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setType("type-updated");
        int updateByPrimaryKeySelective = groupMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Group record = new Group();
        record.setId(Long.valueOf(104));
        record.setName("name-updated");
        record.setType("type-updated");
        int updateByPrimaryKey = groupMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testCountByUniqueKey4Create() {
        Group record = new Group();
        record.setName("name_104");
        record.setType("type_104");
        Long cnt = groupMapper.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testCountByUniqueKey4Update() {
        Group record = new Group();
        record.setId(Long.valueOf(104));
        record.setName("name_103");
        record.setType("type_103");
        Long cnt = groupMapper.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testFind() {
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        search.setType("type_page");
        List<Group> find = groupMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenNoParam() {
        GroupSearch search = new GroupSearch();
        List<Group> find = groupMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testShoudExecuteWhenFirstNameIsNullStr() {
        GroupSearch search = new GroupSearch();
        search.setName("");
        List<Group> find = groupMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testCount() {
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        search.setType("type_page");
        Long count = groupMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        search.setType("type_page");
        search.setLow(0);
        search.setHigh(20);
        List<Group> listGroup = groupMapper.page(search);
        assertEquals(3, listGroup.size());
    }
}
