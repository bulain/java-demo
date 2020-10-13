package com.bulain.common.dao;

import java.util.List;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.Group;
import com.bulain.common.pojo.GroupSearch;

public interface GroupMapper extends PagedMapper<Group, GroupSearch> {
    List<Group> findGroupByLoginId(Long loginId);
    List<Group> findGroupByNoLoginId(Long loginId);
}