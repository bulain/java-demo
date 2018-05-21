package com.bulain.jcr.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jcr.model.Content;

public class ContentServiceImplTest extends ServiceTestCase {
    @Autowired
    private ContentService contentService;

    private static String folderPath = "folderPath";
    
    private static String id;

    @Test
    public void testInsertByPath() {
        Content content = new Content();
        content.setFileName("fileName");
        content.setContentType("contentType");
        content.setCatagory("catagory");
        content.setLang("lang");
        content.setBytes("bytes".getBytes());
        
        contentService.insert(folderPath, "fileName", content);
        
        assertNotNull(content.getId());
        
        id = content.getId();
    }
    
    @Test
    public void testGetById() {
        Content content = contentService.get(id);
        
        assertNotNull(content);
        assertEquals("fileName", content.getFileName());
        assertEquals("contentType", content.getContentType());
        assertEquals("catagory", content.getCatagory());
        assertEquals("lang", content.getLang());
        assertEquals("bytes", new String(content.getBytes()));
    }

    @Test
    public void testGetByPath() {
        Content content = contentService.get(folderPath, "fileName");
        
        assertNotNull(content);
        assertEquals("fileName", content.getFileName());
        assertEquals("contentType", content.getContentType());
        assertEquals("catagory", content.getCatagory());
        assertEquals("lang", content.getLang());
        assertEquals("bytes", new String(content.getBytes()));
    }


    @Test
    public void testUpdateByPath() {
        Content content = new Content();
        content.setFileName("fileNameX");
        content.setContentType("contentTypeX");
        content.setCatagory("catagoryX");
        content.setLang("langX");
        content.setBytes("bytesX".getBytes());
        
        contentService.update(folderPath, "fileName", content);
        
        content = contentService.get(folderPath, "fileName");
        
        assertNotNull(content);
        assertEquals("fileNameX", content.getFileName());
        assertEquals("contentTypeX", content.getContentType());
        assertEquals("catagoryX", content.getCatagory());
        assertEquals("langX", content.getLang());
        assertEquals("bytesX", new String(content.getBytes()));
    }

    @Test
    public void testDeleteByPath() {
        contentService.delete(folderPath, "fileName");
    }

}
