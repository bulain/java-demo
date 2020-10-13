package com.bulain.common.dao;

import com.bulain.common.dao.BasicMapper;
import com.bulain.common.model.GroupPermission;
import com.bulain.common.pojo.GroupSearch;

public interface GroupPermissionMapper extends BasicMapper<GroupPermission> {
    int deleteGroupPermissionByGroupId(Long groupId);
    int deleteGroupPermissionByNotInPermission(GroupSearch search);
}