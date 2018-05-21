package com.bulain.activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.activiti.model.Category;
import com.bulain.activiti.pojo.CategorySearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;

@DataSet(file = "test-data/init_categorys.xml")
public class CategoryServiceTest extends ServiceTestCase {
    @Autowired
    @Qualifier("categoryServiceTx")
    private CategoryService categoryService;

    @Test
    public void testGet() {
        Category category = categoryService.get(Long.valueOf(102));
        assertNotNull(category);

        assertEquals("name_102", category.getName());
        assertEquals("type_102", category.getType());
    }

    @Test
    public void testInsert() {
        Category category = new Category();
        category.setName("name");
        category.setType("type");
        categoryService.insert(category);
    }

    @Test
    public void testUpdate() {
        Category category = new Category();
        category.setId(Long.valueOf(104));
        category.setName("name-updated");
        category.setType("type-updated");
        categoryService.update(category, true);
    }

    @Test
    public void testDelete() {
        categoryService.delete(Long.valueOf(101));
    }

    @Test
    public void testCountByUniqueKey4Create() {
        Category record = new Category();
        record.setName("name_104");
        record.setType("type_104");
        Long cnt = categoryService.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testCountByUniqueKey4Update() {
        Category record = new Category();
        record.setId(Long.valueOf(104));
        record.setName("name_103");
        record.setType("type_103");
        Long cnt = categoryService.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testFind() {
        CategorySearch search = new CategorySearch();
        search.setName("name_page");
        search.setType("type_page");
        List<Category> find = categoryService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        CategorySearch search = new CategorySearch();
        search.setName("name_page");
        search.setType("type_page");
        Long count = categoryService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        CategorySearch search = new CategorySearch();
        search.setName("name_page");
        search.setType("type_page");
        Page page = new Page();
        List<Category> listCategory = categoryService.page(search, page);
        assertEquals(3, listCategory.size());
    }

}
