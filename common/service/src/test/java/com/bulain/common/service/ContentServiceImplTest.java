package com.bulain.common.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Content;
import com.bulain.common.pojo.ContentSearch;

@DataSet(file = "test-data/init_contents.xml")
public class ContentServiceImplTest extends ServiceTestCase {
    @Autowired
    private ContentService contentService;

    @Test
    public void testGetWithoutBLOBs() {
        Content select = contentService.getWithoutBLOBs(Long.valueOf(109));
        assertEquals("file_name_109", select.getFileName());
        assertEquals("content_type_109", select.getContentType());
        assertEquals("catagory_109", select.getCatagory());
        assertEquals("lang_109", select.getLang());
        assertEquals(Long.valueOf(109), select.getRefId());
        assertEquals("ref_name_109", select.getRefName());
        assertNull(select.getBytes());
    }

    @Test
    public void testGet() {
        Content select = contentService.get(Long.valueOf(102));
        assertEquals("file_name_102", select.getFileName());
        assertEquals("content_type_102", select.getContentType());
        assertEquals("catagory_102", select.getCatagory());
        assertEquals("lang_102", select.getLang());
        assertEquals(Long.valueOf(102), select.getRefId());
        assertEquals("ref_name_102", select.getRefName());
        assertNull(select.getBytes());
    }

    @Test
    public void testInsert() {
        Content record = new Content();
        record.setFileName("fileName");
        record.setContentType("contentType");
        record.setCatagory("catagory");
        record.setLang("lang");
        record.setRefId(Long.valueOf(1));
        record.setRefName("refName");
        record.setBytes(new byte[]{'a', 'b', 'c'});
        contentService.insert(record);
    }

    @Test
    public void testUpdate() {
        Content record = new Content();
        record.setId(Long.valueOf(103));
        record.setFileName("fileName-updated");
        record.setContentType("contentType-updated");
        record.setCatagory("catagory-updated");
        record.setLang("lang-updated");
        record.setRefId(Long.valueOf(1));
        record.setRefName("refName-updated");
        record.setBytes(new byte[]{'a', 'b', 'c'});
        contentService.update(record, true);
    }

    @Test
    public void testDelete() {
        contentService.delete(Long.valueOf(101));
    }

    @Test
    public void testFind() {
        ContentSearch search = new ContentSearch();
        search.setFileName("file_name_page");
        search.setContentType("content_type_page");
        search.setCatagory("catagory_page");
        search.setLang("lang_page");
        search.setRefId(Long.valueOf(1));
        search.setRefName("ref_name_page");
        List<Content> find = contentService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        ContentSearch search = new ContentSearch();
        search.setFileName("file_name_page");
        search.setContentType("content_type_page");
        search.setCatagory("catagory_page");
        search.setLang("lang_page");
        search.setRefId(Long.valueOf(1));
        search.setRefName("ref_name_page");
        Long count = contentService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        ContentSearch search = new ContentSearch();
        search.setFileName("file_name_page");
        search.setContentType("content_type_page");
        search.setCatagory("catagory_page");
        search.setLang("lang_page");
        search.setRefId(Long.valueOf(1));
        search.setRefName("ref_name_page");
        Page page = new Page();
        List<Content> page2 = contentService.page(search, page);
        assertEquals(3, page2.size());
    }

}
