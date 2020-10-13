package com.bulain.common.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.GroupLoginMapper;
import com.bulain.common.dao.GroupMapper;
import com.bulain.common.dao.LoginMapper;
import com.bulain.common.dao.PermissionMapper;
import com.bulain.common.model.Group;
import com.bulain.common.model.GroupLogin;
import com.bulain.common.model.Login;
import com.bulain.common.model.Permission;
import com.bulain.common.pojo.LoginSearch;

public class LoginServiceImpl extends PagedServiceImpl<Login, LoginSearch> implements LoginService {
    private LoginMapper loginMapper;
    private GroupLoginMapper groupLoginMapper;
    private PermissionMapper permissionMapper;
    private GroupMapper groupMapper;

    @Override
    protected PagedMapper<Login, LoginSearch> getPagedMapper() {
        return loginMapper;
    }

    // GroupLogin
    public Long countLoginByGroupId(Long groupId) {
        return loginMapper.countLoginByGroupId(groupId);
    }
    public List<Login> findLoginByGroupId(Long groupId) {
        return loginMapper.findLoginByGroupId(groupId);
    }
    public List<Login> findLoginByNoInGroupId(Long groupId) {
        return loginMapper.findLoginByNoGroupId(groupId);
    }
    public void updateGroupLogin(Long loginId, List<Long> listGroupId) {
        LoginSearch search = new LoginSearch();
        search.setLoginId(loginId);
        search.setListGroupId(listGroupId);
        groupLoginMapper.deleteGroupLoginByNotInGroupId(search);

        List<Group> listGroup = groupMapper.findGroupByLoginId(loginId);
        List<Long> listId = new ArrayList<Long>();
        for (Group group : listGroup) {
            listId.add(group.getId());
        }

        for (Long groupId : listGroupId) {
            if (listId.contains(groupId)) {
                continue;
            }
            GroupLogin gl = new GroupLogin();
            gl.setLoginId(loginId);
            gl.setGroupId(groupId);
            groupLoginMapper.insert(gl);
        }
    }

    public Login getLogin(String loginName, String password) {
        LoginSearch search = new LoginSearch();
        search.setLoginName(loginName);
        List<Login> list = loginMapper.find(search);
        if (list.size() > 0) {
            Login login = list.get(0);
            if (login.getHashedPassword().equals(password)) {
                return login;
            }
        }
        return null;
    }

    public List<String> findPermission(Long loginId) {
        Set<String> setPermission = new HashSet<String>();
        List<Group> listGroup = groupMapper.findGroupByLoginId(loginId);
        for (Group grp : listGroup) {
            List<Permission> listPermission = permissionMapper.findPermissionByGroupId(grp.getId());
            for (Permission permission : listPermission) {
                setPermission.add(permission.getPermission());
            }
        }
        return new ArrayList<String>(setPermission);
    }

    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    public void setGroupLoginMapper(GroupLoginMapper groupLoginMapper) {
        this.groupLoginMapper = groupLoginMapper;
    }

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

}
