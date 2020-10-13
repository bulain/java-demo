package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Reference;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ReferenceSearch;

@DataSet(file = "test-data/init_references.xml")
public class ReferenceMapperTest extends ServiceTestCase {
    @Autowired
    private ReferenceMapper referenceMapper;

    @Test
    public void testSelectListByExample() {
        ReferenceSearch search = new ReferenceSearch();
        search.setName("name_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        List<Item> selectListByExample = referenceMapper.selectListByExample(search);
        assertEquals(3, selectListByExample.size());
    }

    @Test
    public void testSelectItemByExample() {
        ReferenceSearch search = new ReferenceSearch();
        search.setName("name_102");
        search.setCode("code_102");
        search.setLang("lang_102");
        search.setCatagory("catagory_102");
        Item selectItemByExample = referenceMapper.selectItemByExample(search);
        assertNotNull(selectItemByExample);
    }

    @Test
    public void testFind() {
        ReferenceSearch search = new ReferenceSearch();
        search.setName("name_page");
        search.setCode("code_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        List<Reference> find = referenceMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        ReferenceSearch search = new ReferenceSearch();
        search.setName("name_page");
        search.setCode("code_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        Long count = referenceMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        ReferenceSearch search = new ReferenceSearch();
        search.setName("name_page");
        search.setCode("code_page");
        search.setLang("lang_page");
        search.setCatagory("catagory_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<Reference> page2 = referenceMapper.page(search);
        assertEquals(3, page2.size());
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = referenceMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Reference record = new Reference();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCatagory("catagory");
        record.setText("text");
        int insert = referenceMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Reference record = new Reference();
        record.setName("name");
        record.setCode("code");
        record.setLang("lang");
        record.setCatagory("catagory");
        record.setText("text");
        int insertSelective = referenceMapper.insertSelective(record);
        assertEquals(1, insertSelective);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Reference selectByPrimaryKey = referenceMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);
        assertEquals("name_102", selectByPrimaryKey.getName());
        assertEquals("code_102", selectByPrimaryKey.getCode());
        assertEquals("text_102", selectByPrimaryKey.getText());
        assertEquals("lang_102", selectByPrimaryKey.getLang());
        assertEquals("catagory_102", selectByPrimaryKey.getCatagory());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Reference record = new Reference();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCatagory("catagory-updated");
        record.setText("text-updated");
        int updateByPrimaryKeySelective = referenceMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Reference record = new Reference();
        record.setId(Long.valueOf(104));
        record.setName("name-updated");
        record.setCode("code-updated");
        record.setLang("lang-updated");
        record.setCatagory("catagory-updated");
        record.setText("text-updated");
        int updateByPrimaryKey = referenceMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

}
