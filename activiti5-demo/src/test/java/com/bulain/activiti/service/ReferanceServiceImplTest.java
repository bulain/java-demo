package com.bulain.activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.activiti.model.Referance;
import com.bulain.activiti.pojo.Item;
import com.bulain.activiti.pojo.ReferanceBean;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;

@DataSet(file = "test-data/init_referances.xml")
public class ReferanceServiceImplTest extends ServiceTestCase {
    @Autowired
    @Qualifier("referanceServiceTx")
    private ReferanceService referanceService;

    @Test
    public void testDelete() {
        referanceService.delete(Long.valueOf(101));
    }

    @Test
    public void testGetLong() {
        Referance referance = referanceService.get(Long.valueOf(102));
        assertNotNull(referance);
        assertEquals("name_102", referance.getName());
        assertEquals("code_102", referance.getCode());
        assertEquals("text_102", referance.getText());
        assertEquals("lang_102", referance.getLang());
        assertEquals("category_102", referance.getCategory());
    }

    @Test
    public void testInsertReferance() {
        Referance record = new Referance();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCategory("category");
        record.setText("text");
        referanceService.insert(record);
    }

    @Test
    public void testInsertReferanceBean() {
        ReferanceBean referanceBean = new ReferanceBean();
        referanceBean.setName("name");
        referanceBean.setCode("code");
        referanceBean.setCategory("category");
        referanceBean.setTextEN("textEN");
        referanceBean.setTextEN("textEN");
        referanceService.insert(referanceBean);
    }

    @Test
    public void testUpdateReferanceBoolean() {
        Referance record = new Referance();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCategory("category-updated");
        record.setText("text-updated");
        referanceService.update(record, true);
    }

    @Test
    public void testGetTextStringStringString() {
        String text = referanceService.getText("name_102", "code_102", "lang_102");
        assertEquals("", text);
    }

    @Test
    public void testGetTextStringStringStringString() {
        String text = referanceService.getText("name_102", "code_102", "lang_102", "category_102");
        assertEquals("text_102", text);
    }

    @Test
    public void testFindItemStringString() {
        List<Item> findItem = referanceService.findItem("name_page", "lang_page");
        assertEquals(1, findItem.size());
    }

    @Test
    public void testFindItemStringStringString() {
        List<Item> findItem = referanceService.findItem("name_page", "lang_page", "category_page");
        assertEquals(4, findItem.size());
    }

    @Test
    public void testCountByUniqueKey4Create() {
        Referance record = new Referance();
        record.setName("name_104");
        record.setCode("code_104");
        record.setLang("lang_104");
        record.setCategory("category_104");
        Long cnt = referanceService.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testCountByUniqueKey4Update() {
        Referance record = new Referance();
        record.setId(Long.valueOf(104));
        record.setName("name_103");
        record.setCode("code_103");
        record.setLang("lang_103");
        record.setCategory("category_103");
        Long cnt = referanceService.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }
}
