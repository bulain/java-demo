package com.bulain.common.dao;

import com.bulain.common.dao.PagedMapper;
import com.bulain.common.model.MailTemplate;
import com.bulain.common.pojo.MailTemplateSearch;

public interface MailTemplateMapper extends PagedMapper<MailTemplate, MailTemplateSearch> {
    int updateByPrimaryKeyWithBLOBs(MailTemplate record);
    MailTemplate selectByPrimaryKeyWithoutBLOBs(Long id);
}