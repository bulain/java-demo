package com.bulain.common.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.MailTemplate;
import com.bulain.common.pojo.MailTemplateSearch;

@DataSet(file = "test-data/init_mail_templates.xml")
public class MailTemplateServiceImplTest extends ServiceTestCase {
    @Autowired
    private MailTemplateService mailTemplateService;

    @Test
    public void testGetWithoutBLOBs() {
        MailTemplate select = mailTemplateService.getWithoutBLOBs(Long.valueOf(109));
        assertEquals("name_109", select.getName());
        assertEquals("lang_109", select.getLang());
        assertEquals("subject_109", select.getSubject());
        assertNull(select.getBody());
    }

    @Test
    public void testFind() {
        MailTemplateSearch search = new MailTemplateSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        List<MailTemplate> find = mailTemplateService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        MailTemplateSearch search = new MailTemplateSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        Long count = mailTemplateService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        MailTemplateSearch search = new MailTemplateSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        Page page = new Page();
        List<MailTemplate> page2 = mailTemplateService.page(search, page);
        assertEquals(3, page2.size());
    }

    @Test
    public void testGet() {
        MailTemplate mailTemplate = mailTemplateService.get(Long.valueOf(109));
        assertNotNull(mailTemplate);
        assertEquals("name_109", mailTemplate.getName());
        assertEquals("lang_109", mailTemplate.getLang());
        assertEquals("subject_109", mailTemplate.getSubject());
        assertNull(mailTemplate.getBody());
    }

    @Test
    public void testInsert() {
        MailTemplate record = new MailTemplate();
        record.setName("name");
        record.setLang("lang");
        record.setSubject("subject");
        record.setBody(new byte[]{'a', 'b', 'c'});
        mailTemplateService.insert(record);
    }

    @Test
    public void testUpdate() {
        MailTemplate record = new MailTemplate();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setLang("lang-updated");
        record.setSubject("subject-updated");
        record.setBody(new byte[]{'a', 'b', 'c'});
        mailTemplateService.update(record, true);
    }

    @Test
    public void testDelete() {
        mailTemplateService.delete(Long.valueOf(101));
    }

}
