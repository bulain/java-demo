package com.bulain.activiti.service;

import com.bulain.activiti.dao.GroupMapper;
import com.bulain.activiti.model.Group;
import com.bulain.activiti.pojo.GroupSearch;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;

public class GroupServiceImpl extends PagedServiceImpl<Group, GroupSearch> implements GroupService {
    private GroupMapper groupMapper;

    @Override
    protected PagedMapper<Group, GroupSearch> getPagedMapper() {
        return groupMapper;
    }

    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }
}
