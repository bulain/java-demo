package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.GroupLogin;
import com.bulain.common.pojo.GroupSearch;
import com.bulain.common.pojo.LoginSearch;

@DataSet(file = "test-data/init_group_logins.xml")
public class GroupLoginMapperTest extends ServiceTestCase {
    @Autowired
    private GroupLoginMapper groupLoginMapper;

    @Test
    public void testDeleteGroupLoginByLoginId() {
        int deleteGroupLoginByLoginId = groupLoginMapper.deleteGroupLoginByLoginId(Long.valueOf(101));
        assertEquals(3, deleteGroupLoginByLoginId);
    }

    @Test
    public void testDeleteGroupLoginByNotInLoginId() {
        GroupSearch search = new GroupSearch();
        search.setGroupId(Long.valueOf(101));
        search.setListLoginId(Arrays.asList(new Long[]{101L, 102L}));
        int deleteGroupLoginByNotInLoginId = groupLoginMapper.deleteGroupLoginByNotInLoginId(search);
        assertEquals(1, deleteGroupLoginByNotInLoginId);
    }

    @Test
    public void testDeleteGroupLoginByGroupId() {
        int deleteGroupLoginByGroupId = groupLoginMapper.deleteGroupLoginByGroupId(Long.valueOf(101));
        assertEquals(2, deleteGroupLoginByGroupId);
    }

    @Test
    public void testDeleteGroupLoginByNotInGroupId() {
        LoginSearch search = new LoginSearch();
        search.setLoginId(Long.valueOf(101));
        search.setListGroupId(Arrays.asList(new Long[]{101L, 102L}));
        int deleteGroupLoginByNotInGroupId = groupLoginMapper.deleteGroupLoginByNotInGroupId(search);
        assertEquals(1, deleteGroupLoginByNotInGroupId);
    }

    @Test
    public void testDeleteGroupLogin() {
        LoginSearch search = new LoginSearch();
        search.setLoginId(Long.valueOf(101));
        search.setGroupId(Long.valueOf(101));
        int deleteGroupLogin = groupLoginMapper.deleteGroupLogin(search);
        assertEquals(1, deleteGroupLogin);
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = groupLoginMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        GroupLogin record = new GroupLogin();
        record.setLoginId(Long.valueOf(105));
        record.setGroupId(Long.valueOf(105));
        int insert = groupLoginMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        GroupLogin record = new GroupLogin();
        record.setLoginId(Long.valueOf(105));
        record.setGroupId(Long.valueOf(105));
        int insertSelective = groupLoginMapper.insertSelective(record);
        assertEquals(1, insertSelective);
    }

    @Test
    public void testSelectByPrimaryKey() {
        GroupLogin select = groupLoginMapper.selectByPrimaryKey(Long.valueOf(102));
        assertEquals(Long.valueOf(102), select.getLoginId());
        assertEquals(Long.valueOf(102), select.getGroupId());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        GroupLogin record = new GroupLogin();
        record.setId(Long.valueOf(103));
        record.setLoginId(Long.valueOf(103));
        record.setGroupId(Long.valueOf(103));
        int updateByPrimaryKeySelective = groupLoginMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        GroupLogin record = new GroupLogin();
        record.setId(Long.valueOf(104));
        record.setLoginId(Long.valueOf(104));
        record.setGroupId(Long.valueOf(104));
        int updateByPrimaryKey = groupLoginMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

}
