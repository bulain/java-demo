package com.bulain.common.service;

import com.bulain.common.service.PagedService;
import com.bulain.common.model.MailTemplate;
import com.bulain.common.pojo.MailTemplateSearch;

public interface MailTemplateService extends PagedService<MailTemplate, MailTemplateSearch> {
    MailTemplate getWithoutBLOBs(Long id);
}
