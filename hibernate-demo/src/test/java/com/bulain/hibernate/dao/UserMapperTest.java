package com.bulain.hibernate.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
import com.bulain.hibernate.entity.User;
import com.bulain.hibernate.pojo.UserSearch;

@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_users.xml")
public class UserMapperTest extends DaoTestCase {
    
    @Autowired
    private UserMapper userMapper;
    
    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = userMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }
    
    @Test
    public void testInsert() {
        User record = new User();
        record.setFirstName("firstName");
        record.setLastName("lastName");
        int insert = userMapper.insert(record);
        assertEquals(1, insert);
        assertNotNull(record.getId());
    }
    
    @Test
    public void testSelectByPrimaryKey() {
        User selectByPrimaryKey = userMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);
        
        assertEquals("first_name_102", selectByPrimaryKey.getFirstName());
        assertEquals("last_name_102", selectByPrimaryKey.getLastName());
    }
    
    @Test
    public void testUpdateByPrimaryKey() {
        User record = userMapper.selectByPrimaryKey(104L);
        record.setFirstName("firstName-updated");
        record.setLastName("lastName-updated");
        int updateByPrimaryKey = userMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }
    
    @Test
    public void testFind() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        
        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(User.class).add(example);
        OrderBy orderBy = new OrderBy();
        orderBy.setOrderBy("firstName");
        orderBy.setSequance("asc");
        
        List<User> find = userMapper.find(dc, orderBy);
        assertEquals(3, find.size());
    }
    
    @Test
    public void testCount() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        
        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(User.class).add(example);
        
        Long count = userMapper.count(dc);
        assertEquals(Long.valueOf(3), count);
    }
    
    @Test
    public void testPage() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        
        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(User.class).add(example);
        Page page = new Page();
        OrderBy orderBy = new OrderBy();
        orderBy.setOrderBy("firstName");
        orderBy.setSequance("asc");
        
        List<User> listUser = userMapper.page(dc, page, orderBy);
        assertEquals(3, listUser.size());
    }
    
    @Test
    public void testDynamicFind() {
        UserSearch search = new UserSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        search.setOrderBy("firstName");
        search.setSequance("desc");
        
        List<User> list = userMapper.find("dynamicFind", search);
        assertEquals(3, list.size());
    }
    
    @Test
    public void testDynamicCount() {
        UserSearch search = new UserSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        search.setOrderBy("firstName");
        search.setSequance("desc");
        
        Long cnt = userMapper.count("dynamicFind", search);
        assertEquals(Long.valueOf(3), cnt);
    }
    
    @Test
    public void testDynamicPage() {
        UserSearch search = new UserSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        search.setOrderBy("firstName");
        search.setSequance("desc");
        search.setLow(0);
        search.setHigh(10);
        
        List<User> list = userMapper.page("dynamicFind", search);
        assertEquals(3, list.size());
    }
}
