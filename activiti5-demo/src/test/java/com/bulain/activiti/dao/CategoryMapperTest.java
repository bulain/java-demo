package com.bulain.activiti.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.activiti.model.Category;
import com.bulain.activiti.pojo.CategorySearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.DaoTestCase;

@DataSet(file = "test-data/init_categorys.xml")
public class CategoryMapperTest extends DaoTestCase {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = categoryMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Category record = new Category();
        record.setName("name");
        record.setType("type");
        int insert = categoryMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Category record = new Category();
        record.setName("name");
        record.setType("type");
        int insert = categoryMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Category selectByPrimaryKey = categoryMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);

        assertEquals("name_102", selectByPrimaryKey.getName());
        assertEquals("type_102", selectByPrimaryKey.getType());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Category record = new Category();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setType("type-updated");
        int updateByPrimaryKeySelective = categoryMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testCountByUniqueKey4Create() {
        Category record = new Category();
        record.setName("name_104");
        record.setType("type_104");
        Long cnt = categoryMapper.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testCountByUniqueKey4Update() {
        Category record = new Category();
        record.setId(Long.valueOf(104));
        record.setName("name_103");
        record.setType("type_103");
        Long cnt = categoryMapper.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Category record = new Category();
        record.setId(Long.valueOf(104));
        record.setName("name-updated");
        record.setType("type-updated");
        int updateByPrimaryKey = categoryMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        CategorySearch search = new CategorySearch();
        search.setName("name_page");
        List<Category> find = categoryMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenNoParam() {
        CategorySearch search = new CategorySearch();
        List<Category> find = categoryMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testShoudExecuteWhenFirstNameIsNullStr() {
        CategorySearch search = new CategorySearch();
        search.setName("");
        List<Category> find = categoryMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testCount() {
        CategorySearch search = new CategorySearch();
        search.setName("name_page");
        Long count = categoryMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        CategorySearch search = new CategorySearch();
        search.setName("name_page");
        search.setLow(0);
        search.setHigh(20);
        List<Category> listCategory = categoryMapper.page(search);
        assertEquals(3, listCategory.size());
    }

}