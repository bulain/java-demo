package com.bulain.common.service;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;
import com.bulain.common.dao.ProfileMapper;
import com.bulain.common.model.Profile;
import com.bulain.common.pojo.ProfileSearch;

public class ProfileServiceImpl extends PagedServiceImpl<Profile, ProfileSearch> implements ProfileService {
    private ProfileMapper profileMapper;

    @Override
    protected PagedMapper<Profile, ProfileSearch> getPagedMapper() {
        return profileMapper;
    }

    public void setProfileMapper(ProfileMapper profileMapper) {
        this.profileMapper = profileMapper;
    }
}
