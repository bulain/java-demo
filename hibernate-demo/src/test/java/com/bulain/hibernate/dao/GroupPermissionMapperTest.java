package com.bulain.hibernate.dao;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.DaoTestCase;
import com.bulain.hibernate.entity.GroupPermission;

@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_group_permissions.xml")
public class GroupPermissionMapperTest extends DaoTestCase {
    @Autowired
    private GroupPermissionMapper groupPermissionMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = groupPermissionMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        GroupPermission record = new GroupPermission();
        record.setPermission("permission");
        int insert = groupPermissionMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        GroupPermission select = groupPermissionMapper.selectByPrimaryKey(Long.valueOf(102));
        assertEquals("permission_102", select.getPermission());
    }

    @Test
    public void testUpdateByPrimaryKey() {
        GroupPermission record = groupPermissionMapper.selectByPrimaryKey(103L);
        record.setPermission("permission-updated");
        int updateByPrimaryKey = groupPermissionMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

}
