package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.model.User;
import com.bulain.common.test.ServiceTestCase;

public class UserMapperImplTest extends ServiceTestCase{
    @Autowired
    private UserMapper userMapper;
    
    @Before
    public void setUp(){
        Map<Long, User> mapUser = new HashMap<Long, User>();
        
        User user1 = new User();
        user1.setId(1L);
        mapUser.put(user1.getId(), user1);
        
        User user2 = new User();
        user2.setId(2L);
        mapUser.put(user2.getId(), user2);
        
        userMapper.setMapUser(mapUser);
    }
    
    @After
    public void tearDown(){
        userMapper.clearMapUser();
    }
    
    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = userMapper.deleteByPrimaryKey(1L);
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(3L);
        int insert = userMapper.insert(user);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        User user = new User();
        user.setId(3L);
        int insert = userMapper.insertSelective(user);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        User selectByPrimaryKey = userMapper.selectByPrimaryKey(1L);
        assertEquals(Long.valueOf(1), selectByPrimaryKey.getId());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        User user = new User();
        user.setId(1L);
        int updateByPrimaryKey = userMapper.updateByPrimaryKeySelective(user);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        User user = new User();
        user.setId(1L);
        int updateByPrimaryKey = userMapper.updateByPrimaryKey(user);
        assertEquals(1, updateByPrimaryKey);
    }

    public void setUserMapper(UserMapperImpl userMapper) {
        this.userMapper = userMapper;
    }

}
