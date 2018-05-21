package com.bulain.jbpm4order.dao;

import com.bulain.jbpm4order.model.MailTemplate;

public interface MailTemplateMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MailTemplate record);

    int insertSelective(MailTemplate record);

    MailTemplate selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MailTemplate record);

    int updateByPrimaryKeyWithBLOBs(MailTemplate record);

    int updateByPrimaryKey(MailTemplate record);
}