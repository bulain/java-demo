package com.bulain.common.service;

import java.util.ArrayList;
import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.GroupLoginMapper;
import com.bulain.common.dao.GroupMapper;
import com.bulain.common.dao.GroupPermissionMapper;
import com.bulain.common.dao.LoginMapper;
import com.bulain.common.dao.PermissionMapper;
import com.bulain.common.model.Group;
import com.bulain.common.model.GroupLogin;
import com.bulain.common.model.GroupPermission;
import com.bulain.common.model.Login;
import com.bulain.common.model.Permission;
import com.bulain.common.pojo.GroupSearch;

public class GroupServiceImpl extends PagedServiceImpl<Group, GroupSearch> implements GroupService {
    private GroupMapper groupMapper;
    private GroupLoginMapper groupLoginMapper;
    private GroupPermissionMapper groupPermissionMapper;
    private PermissionMapper permissionMapper;
    private LoginMapper loginMapper;

    @Override
    protected PagedMapper<Group, GroupSearch> getPagedMapper() {
        return groupMapper;
    }

    // GroupLogin
    public List<Group> findGroupByLoginId(Long loginId) {
        return groupMapper.findGroupByLoginId(loginId);
    }
    public List<Group> findGroupByNoLoginId(Long loginId) {
        return groupMapper.findGroupByNoLoginId(loginId);
    }
    public void updateGroupLogin(Long groupId, List<Long> listLoginId) {
        GroupSearch search = new GroupSearch();
        search.setGroupId(groupId);
        search.setListLoginId(listLoginId);
        groupLoginMapper.deleteGroupLoginByNotInLoginId(search);

        List<Login> listLogin = loginMapper.findLoginByGroupId(groupId);
        List<Long> listId = new ArrayList<Long>();
        for (Login l : listLogin) {
            listId.add(l.getId());
        }

        for (Long loginId : listLoginId) {
            if (listId.contains(loginId)) {
                continue;
            }
            GroupLogin gl = new GroupLogin();
            gl.setLoginId(loginId);
            gl.setGroupId(groupId);
            groupLoginMapper.insert(gl);
        }
    }

    // GroupPermission
    public List<Permission> findPermissionByGroupId(Long groupId) {
        return permissionMapper.findPermissionByGroupId(groupId);
    }
    public List<Permission> findPermissionByNoGroupId(Long groupId) {
        return permissionMapper.findPermissionByNoGroupId(groupId);
    }

    public void updateGroupPermission(Long groupId, List<String> listPermission) {
        GroupSearch search = new GroupSearch();
        search.setGroupId(groupId);
        search.setListPermission(listPermission);
        groupPermissionMapper.deleteGroupPermissionByNotInPermission(search);

        List<Permission> listTemp = permissionMapper.findPermissionByGroupId(groupId);
        List<String> listStr = new ArrayList<String>();
        for (Permission gp : listTemp) {
            listStr.add(gp.getPermission());
        }

        for (String permission : listPermission) {
            if (listStr.contains(permission)) {
                continue;
            }
            GroupPermission gp = new GroupPermission();
            gp.setGroupId(groupId);
            gp.setPermission(permission);
            groupPermissionMapper.insert(gp);
        }
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public void setGroupLoginMapper(GroupLoginMapper groupLoginMapper) {
        this.groupLoginMapper = groupLoginMapper;
    }

    public void setGroupPermissionMapper(GroupPermissionMapper groupPermissionMapper) {
        this.groupPermissionMapper = groupPermissionMapper;
    }

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }
}