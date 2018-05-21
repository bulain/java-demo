package com.bulain.activiti.service;

import com.bulain.activiti.dao.JodaMapper;
import com.bulain.activiti.model.Joda;
import com.bulain.activiti.pojo.JodaSearch;
import com.bulain.common.dao.PagedMapper;
import com.bulain.common.service.PagedServiceImpl;

public class JodaServiceImpl extends PagedServiceImpl<Joda, JodaSearch> implements JodaService {
    private JodaMapper jodaMapper;

    public void setJodaMapper(JodaMapper jodaMapper) {
        this.jodaMapper = jodaMapper;
    }
    @Override
    public PagedMapper<Joda, JodaSearch> getPagedMapper() {
        return jodaMapper;
    }
}
