package com.bulain.common.service;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dao.UserMapper;
import com.bulain.common.model.User;
import com.bulain.common.test.ServiceTestCase;

public class UserServiceImplTest extends ServiceTestCase {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserService userService;
    
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
    public void testGet() {
        User user = userService.get(1L);
        assertNotNull(user);
    }

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(3L);
        userService.insert(user);
    }

    @Test
    public void testUpdate() {
        User user = new User();
        user.setId(1L);
        userService.update(user, true);
    }

    @Test
    public void testDelete() {
        userService.delete(1L);
    }

}
