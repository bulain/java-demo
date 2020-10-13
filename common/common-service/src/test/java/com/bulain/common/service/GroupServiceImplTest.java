package com.bulain.common.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Group;
import com.bulain.common.model.Permission;
import com.bulain.common.pojo.GroupSearch;

@DataSet(file = "test-data/init_groups.xml")
public class GroupServiceImplTest extends ServiceTestCase {
    @Autowired
    private GroupService groupService;

    @Test
    public void testFindGroupByLoginId() {
        List<Group> findGroupByLoginId = groupService.findGroupByLoginId(Long.valueOf(105));
        assertEquals(3, findGroupByLoginId.size());
    }

    @Test
    public void testFindGroupByNoLoginId() {
        List<Group> findGroupByNoLoginId = groupService.findGroupByNoLoginId(Long.valueOf(105));
        assertEquals(4, findGroupByNoLoginId.size());
    }

    @Test
    public void testUpdateGroupLogin() {
        Long groupId = Long.valueOf(102);
        List<Long> listLoginId = Arrays.asList(new Long[]{102L, 105L});
        groupService.updateGroupLogin(groupId, listLoginId);
    }

    @Test
    public void testFindPermissionByGroupId() {
        List<Permission> findPermissionByGroupId = groupService.findPermissionByGroupId(Long.valueOf(105));
        assertEquals(3, findPermissionByGroupId.size());
    }

    @Test
    public void testFindPermissionByNoGroupId() {
        List<Permission> findPermissionByNoGroupId = groupService.findPermissionByNoGroupId(Long.valueOf(105));
        assertEquals(2, findPermissionByNoGroupId.size());
    }

    @Test
    public void testUpdateGroupPermission() {
        Long groupId = Long.valueOf(102);
        List<String> listPermission = Arrays.asList(new String[]{"permission_102", "permission_103"});
        groupService.updateGroupPermission(groupId, listPermission);
    }

    @Test
    public void testFind() {
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        List<Group> find = groupService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        Long count = groupService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        GroupSearch search = new GroupSearch();
        search.setName("name_page");
        Page page = new Page();
        List<Group> page2 = groupService.page(search, page);
        assertEquals(3, page2.size());
    }

    @Test
    public void testGet() {
        Group group = groupService.get(Long.valueOf(102));
        assertEquals("name_102", group.getName());
        assertEquals("note_102", group.getNote());
    }

    @Test
    public void testInsert() {
        Group record = new Group();
        record.setName("name");
        record.setNote("note");
        groupService.insert(record);
    }

    @Test
    public void testUpdate() {
        Group record = new Group();
        record.setId(Long.valueOf(103));
        record.setName("name");
        record.setNote("note");
        groupService.update(record, true);
    }

    @Test
    public void testDelete() {
        groupService.delete(Long.valueOf(101));
    }

}
