package com.bulain.common.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Reference;
import com.bulain.common.model.ReferenceBean;
import com.bulain.common.pojo.Item;

@DataSet(file = "test-data/init_references.xml")
public class ReferenceServiceImplTest extends ServiceTestCase {
    @Autowired
    private ReferenceService referenceService;

    @Test
    public void testDelete() {
        referenceService.delete(Long.valueOf(101));
    }

    @Test
    public void testGetLong() {
        Reference reference = referenceService.get(Long.valueOf(102));
        assertNotNull(reference);
        assertEquals("name_102", reference.getName());
        assertEquals("code_102", reference.getCode());
        assertEquals("text_102", reference.getText());
        assertEquals("lang_102", reference.getLang());
        assertEquals("catagory_102", reference.getCatagory());
    }

    @Test
    public void testInsertReference() {
        Reference record = new Reference();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCatagory("catagory");
        record.setText("text");
        referenceService.insert(record);
    }

    @Test
    public void testInsertReferenceBean() {
        ReferenceBean referenceBean = new ReferenceBean();
        referenceBean.setName("name");
        referenceBean.setCode("code");
        referenceBean.setCatagory("catagory");
        referenceBean.setTextEN("textEN");
        referenceBean.setTextEN("textEN");
        referenceService.insert(referenceBean);
    }

    @Test
    public void testUpdateReferenceBoolean() {
        Reference record = new Reference();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCatagory("catagory-updated");
        record.setText("text-updated");
        referenceService.update(record, true);
    }

    @Test
    public void testGetTextStringStringString() {
        String text = referenceService.getText("name_102", "code_102", "lang_102");
        assertEquals("", text);
    }

    @Test
    public void testGetTextStringStringStringString() {
        String text = referenceService.getText("name_102", "code_102", "lang_102", "catagory_102");
        assertEquals("text_102", text);
    }
    
    @Test
    public void testGetItemStringStringString() {
        Item item = referenceService.getItem("name_102", "code_102", "lang_102");
        assertEquals("code_102", item.getKey());
        assertEquals("", item.getValue());
    }

    @Test
    public void testGetItemStringStringStringString() {
        Item item = referenceService.getItem("name_102", "code_102", "lang_102", "catagory_102");
        assertEquals("code_102", item.getKey());
        assertEquals("text_102", item.getValue());
    }

    @Test
    public void testFindItemStringString() {
        List<Item> findItem = referenceService.findItem("name_page", "lang_page");
        assertEquals(1, findItem.size());
    }

    @Test
    public void testFindItemStringStringString() {
        List<Item> findItem = referenceService.findItem("name_page", "lang_page", "catagory_page");
        assertEquals(4, findItem.size());
    }

}
