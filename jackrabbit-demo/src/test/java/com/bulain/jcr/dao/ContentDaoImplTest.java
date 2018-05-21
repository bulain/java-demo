package com.bulain.jcr.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.test.ServiceTestCase;
import com.bulain.jcr.model.Content;

public class ContentDaoImplTest extends ServiceTestCase {
    private static String folderPath = "a1/a2/a3";
    
    @Autowired
    private ContentDao contentDao;
    
    private static String id;

    @Test
    public void testInsertByPath() {
        Content content = new Content();
        content.setFileName("fileName");
        content.setContentType("contentType");
        content.setCatagory("catagory");
        content.setLang("lang");
        content.setBytes("bytes".getBytes());
        
        contentDao.insertByPath(folderPath, "fileName", content);
        
        assertNotNull(content.getId());
        
        id = content.getId();
    }
    
    @Test
    public void testInsertByPathLevel3() {
        Content content = new Content();
        content.setFileName("fileName");
        content.setContentType("contentType");
        content.setCatagory("catagory");
        content.setLang("lang");
        content.setBytes("bytes".getBytes());
        
        contentDao.insertByPath(folderPath, "fileName", content);
        
        assertNotNull(content.getId());
        
        id = content.getId();
    }
    
    @Test
    public void testGetById() {
        Content content = contentDao.getById(id);
        
        assertNotNull(content);
        assertEquals("fileName", content.getFileName());
        assertEquals("contentType", content.getContentType());
        assertEquals("catagory", content.getCatagory());
        assertEquals("lang", content.getLang());
        assertEquals("bytes", new String(content.getBytes()));
    }

    @Test
    public void testGetByPath() {
        Content content = contentDao.getByPath(folderPath, "fileName");
        
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
        
        contentDao.updateByPath(folderPath, "fileName", content);
        
        content = contentDao.getByPath(folderPath, "fileName");
        
        assertNotNull(content);
        assertEquals("fileNameX", content.getFileName());
        assertEquals("contentTypeX", content.getContentType());
        assertEquals("catagoryX", content.getCatagory());
        assertEquals("langX", content.getLang());
        assertEquals("bytesX", new String(content.getBytes()));
    }

    @Test
    public void testDeleteByPath() {
        contentDao.deleteByPath(folderPath, "fileName");
    }

}
