package com.bulain.hibernate.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.DaoTestCase;
import com.bulain.hibernate.entity.Group;
import com.bulain.hibernate.entity.GroupUser;
import com.bulain.hibernate.entity.User;

@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_group_users.xml")
public class GroupUserMapperTest extends DaoTestCase {
    @Autowired
    private GroupUserMapper groupUserMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GroupMapper groupMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = groupUserMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        GroupUser record = new GroupUser();
        User user = userMapper.selectByPrimaryKey(105L);
        record.setUser(user);
        Group group = groupMapper.selectByPrimaryKey(105L);
        record.setGroup(group);
        int insert = groupUserMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        GroupUser select = groupUserMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(select);
        assertEquals("first_name_102", select.getUser().getFirstName());
        assertEquals("last_name_102", select.getUser().getLastName());
        assertEquals("name_102", select.getGroup().getName());
    }

    @Test
    public void testUpdateByPrimaryKey() {
        GroupUser record = groupUserMapper.selectByPrimaryKey(104L);
        Group group = groupMapper.selectByPrimaryKey(105L);
        record.setGroup(group);
        int updateByPrimaryKey = groupUserMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

}
