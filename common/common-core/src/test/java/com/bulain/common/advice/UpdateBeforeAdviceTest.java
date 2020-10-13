package com.bulain.common.advice;

import static org.junit.Assert.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dao.UserMapper;
import com.bulain.common.model.User;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.util.SystemClock;


public class UpdateBeforeAdviceTest extends ServiceTestCase{
    @Autowired
    private UserMapper userMapper;
    private DateTime current;

    @Before
    public void setUp() {
        current = SystemClock.getDateTime();
        DateTimeUtils.setCurrentMillisFixed(current.getMillis());
    }

    @After
    public void tearDown() {
        userMapper.clearMapUser();
        DateTimeUtils.setCurrentMillisSystem();
    }
    
    @Test
    public void testUpdateByPrimaryKey(){
        User user = new User();
        user.setId(3L);
        userMapper.insertSelective(user);
        userMapper.updateByPrimaryKey(user);
        
        user = userMapper.selectByPrimaryKey(3L);
        assertEquals(current.toDate(), user.getCreatedAt());
        assertEquals(current.toDate(), user.getUpdatedAt());
        assertEquals("createdBy", user.getCreatedBy());
        assertEquals("updatedBy", user.getUpdatedBy());
    }
    
    @Test
    public void testUpdateByPrimaryKeySelective(){
        User user = new User();
        user.setId(3L);
        userMapper.insertSelective(user);
        userMapper.updateByPrimaryKeySelective(user);
        
        user = userMapper.selectByPrimaryKey(3L);
        assertEquals(current.toDate(), user.getCreatedAt());
        assertEquals(current.toDate(), user.getUpdatedAt());
        assertEquals("createdBy", user.getCreatedBy());
        assertEquals("updatedBy", user.getUpdatedBy());
    }
}
