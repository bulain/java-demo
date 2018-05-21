package com.bulain.common.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.Permission;
import com.bulain.common.pojo.PermissionSearch;

public interface PermissionMapper extends PagedMapper<Permission, PermissionSearch> {
    List<Permission> findPermissionByGroupId(Long groupId);
    List<Permission> findPermissionByNoGroupId(Long groupId);
}