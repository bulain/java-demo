package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
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
public class MailTemplateMapperTest extends ServiceTestCase {
    @Autowired
    private MailTemplateMapper mailTemplateMapper;

    @Test
    public void testUpdateByPrimaryKeyWithBLOBs() {
        MailTemplate record = new MailTemplate();
        record.setId(Long.valueOf(108));
        record.setName("name-updated");
        record.setLang("lang-updated");
        record.setSubject("subject-updated");
        record.setBody(new byte[]{'a', 'b', 'c'});
        int updateByPrimaryKey = mailTemplateMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testSelectByPrimaryKeyWithoutBLOBs() {
        MailTemplate select = mailTemplateMapper.selectByPrimaryKeyWithoutBLOBs(Long.valueOf(109));
        assertEquals("name_109", select.getName());
        assertEquals("lang_109", select.getLang());
        assertEquals("subject_109", select.getSubject());
        assertNull(select.getBody());
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = mailTemplateMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        MailTemplate record = new MailTemplate();
        record.setName("name");
        record.setLang("lang");
        record.setSubject("subject");
        record.setBody(new byte[]{'a', 'b', 'c'});
        int insert = mailTemplateMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        MailTemplate record = new MailTemplate();
        record.setName("name");
        record.setLang("lang");
        record.setSubject("subject");
        record.setBody(new byte[]{'a', 'b', 'c'});
        int insertSelective = mailTemplateMapper.insertSelective(record);
        assertEquals(1, insertSelective);
    }

    @Test
    public void testSelectByPrimaryKey() {
        MailTemplate select = mailTemplateMapper.selectByPrimaryKey(Long.valueOf(102));
        assertEquals("name_102", select.getName());
        assertEquals("lang_102", select.getLang());
        assertEquals("subject_102", select.getSubject());
        assertNull(select.getBody());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        MailTemplate record = new MailTemplate();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setLang("lang-updated");
        record.setSubject("subject-updated");
        record.setBody(new byte[]{'a', 'b', 'c'});
        int updateByPrimaryKeySelective = mailTemplateMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        MailTemplate record = new MailTemplate();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setLang("lang-updated");
        record.setSubject("subject-updated");
        record.setBody(new byte[]{'a', 'b', 'c'});
        int updateByPrimaryKey = mailTemplateMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        MailTemplateSearch search = new MailTemplateSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        List<MailTemplate> find = mailTemplateMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        MailTemplateSearch search = new MailTemplateSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        Long count = mailTemplateMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        MailTemplateSearch search = new MailTemplateSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<MailTemplate> page2 = mailTemplateMapper.page(search);
        assertEquals(3, page2.size());
    }

}
