package com.bulain.hibernate.service;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;

import com.bulain.hibernate.core.BasicMapper;
import com.bulain.hibernate.core.BasicServiceImpl;
import com.bulain.hibernate.dao.GroupUserMapper;
import com.bulain.hibernate.entity.GroupUser;

public class GroupUserServiceImpl extends BasicServiceImpl<GroupUser> implements GroupUserService{

    private GroupUserMapper groupUserMapper;
    
    public long countByDuplicate(GroupUser record) {
        GroupUser gu = new GroupUser();
        gu.setGroup(record.getGroup());
        gu.setUser(record.getUser());
        DetachedCriteria dc = DetachedCriteria.forClass(GroupUser.class).add(Example.create(gu));
        return groupUserMapper.count(dc);
    }

    @Override
    protected BasicMapper<GroupUser> getBasicMapper() {
        return groupUserMapper;
    }

    public void setGroupUserMapper(GroupUserMapper groupUserMapper) {
        this.groupUserMapper = groupUserMapper;
    }
}
