package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Permission;
import com.bulain.common.pojo.PermissionSearch;

@DataSet(file = "test-data/init_permissions.xml")
public class PermissionMapperTest extends ServiceTestCase {
    @Autowired
    private PermissionMapper permissionMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = permissionMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Permission record = new Permission();
        record.setPermission("permission");
        int insert = permissionMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Permission record = new Permission();
        record.setPermission("permission");
        int insert = permissionMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Permission selectByPrimaryKey = permissionMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);

        assertEquals("permission_102", selectByPrimaryKey.getPermission());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Permission record = new Permission();
        record.setId(Long.valueOf(103));
        record.setPermission("permission-updated");
        int updateByPrimaryKeySelective = permissionMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Permission record = new Permission();
        record.setId(Long.valueOf(104));
        record.setPermission("permission-updated");
        int updateByPrimaryKey = permissionMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        PermissionSearch search = new PermissionSearch();
        search.setPermission("permission_page");
        List<Permission> find = permissionMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        PermissionSearch search = new PermissionSearch();
        search.setPermission("permission_page");
        Long count = permissionMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        PermissionSearch search = new PermissionSearch();
        search.setPermission("permission_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<Permission> listPermission = permissionMapper.page(search);
        assertEquals(3, listPermission.size());
    }

    @Test
    public void testFindPermissionByGroupId() {
        List<Permission> findPermissionByGroupId = permissionMapper.findPermissionByGroupId(Long.valueOf(105));
        assertEquals(3, findPermissionByGroupId.size());
    }

    @Test
    public void testFindPermissionByNoGroupId() {
        List<Permission> findPermissionByNoGroupId = permissionMapper.findPermissionByNoGroupId(Long.valueOf(105));
        assertEquals(4, findPermissionByNoGroupId.size());
    }
}
