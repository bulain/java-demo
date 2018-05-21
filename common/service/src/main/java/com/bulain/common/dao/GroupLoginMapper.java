package com.bulain.common.dao;

import com.bulain.common.dao.BasicMapper;
import com.bulain.common.model.GroupLogin;
import com.bulain.common.pojo.GroupSearch;
import com.bulain.common.pojo.LoginSearch;

public interface GroupLoginMapper extends BasicMapper<GroupLogin> {
    int deleteGroupLoginByLoginId(Long loginId);
    int deleteGroupLoginByNotInLoginId(GroupSearch search);

    int deleteGroupLoginByGroupId(Long groupId);
    int deleteGroupLoginByNotInGroupId(LoginSearch search);

    int deleteGroupLogin(LoginSearch search);
}