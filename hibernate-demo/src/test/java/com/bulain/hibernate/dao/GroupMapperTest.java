package com.bulain.hibernate.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.page.OrderBy;
import com.bulain.common.page.Page;
import com.bulain.common.test.DaoTestCase;
import com.bulain.hibernate.entity.Group;
import com.bulain.hibernate.pojo.GroupSearch;

@SeedDataSet(file = "test-data/init_seed_dataset.xml")
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
        record.setNote("note");
        int insert = groupMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Group select = groupMapper.selectByPrimaryKey(Long.valueOf(102));
        assertEquals("name_102", select.getName());
        assertEquals("note_102", select.getNote());
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Group record = groupMapper.selectByPrimaryKey(103L);
        record.setName("name");
        record.setNote("note");
        int updateByPrimaryKey = groupMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        Group search = new Group();
        search.setName("name_page");
        OrderBy orderBy = new OrderBy();
        orderBy.setOrderBy("name");
        orderBy.setSequance("asc");

        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(Group.class).add(example);

        List<Group> find = groupMapper.find(dc, orderBy);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        Group search = new Group();
        search.setName("name_page");

        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(Group.class).add(example);

        Long count = groupMapper.count(dc);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        Group search = new Group();
        search.setName("name_page");
        Page page = new Page();
        page.setCount(10);
        OrderBy orderBy = new OrderBy();
        orderBy.setOrderBy("name");
        orderBy.setSequance("asc");

        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(Group.class).add(example);

        List<Group> page2 = groupMapper.page(dc, page, orderBy);
        assertEquals(3, page2.size());
    }

    @Test
    public void testDynamicFind(){
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        search.setOrderBy("name");
        
        List<Group> list = groupMapper.find("dynamicFind", search);
        assertEquals(3, list.size());
    }
    
    @Test
    public void testDynamicCount(){
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        search.setOrderBy("name");
        
        Long cnt = groupMapper.count("dynamicFind", search);
        assertEquals(Long.valueOf(3), cnt);
    }
    
    @Test
    public void testDynamicPage(){
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        search.setOrderBy("name");
        search.setLow(0);
        search.setHigh(10);
        
        List<Group> list = groupMapper.page("dynamicFind", search);
        assertEquals(3, list.size());
    }
}
