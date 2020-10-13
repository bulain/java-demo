package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Person;
import com.bulain.common.pojo.PersonSearch;

@DataSet(file = "test-data/init_persons.xml")
public class PersonMapperTest extends ServiceTestCase {
    @Autowired
    private PersonMapper personMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = personMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Person record = new Person();
        record.setFirstName("firstName");
        record.setLastName("lastName");
        int insert = personMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Person record = new Person();
        record.setFirstName("firstName");
        record.setLastName("lastName");
        int insert = personMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Person selectByPrimaryKey = personMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);
        assertEquals("first_name_102", selectByPrimaryKey.getFirstName());
        assertEquals("last_name_102", selectByPrimaryKey.getLastName());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Person record = new Person();
        record.setId(Long.valueOf(103));
        record.setFirstName("firstName-updated");
        record.setLastName("lastName-updated");
        int updateByPrimaryKeySelective = personMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Person record = new Person();
        record.setId(Long.valueOf(104));
        record.setFirstName("firstName-updated");
        record.setLastName("lastName-updated");
        int updateByPrimaryKey = personMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        PersonSearch search = new PersonSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        List<Person> find = personMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenNoParam() {
        PersonSearch search = new PersonSearch();
        List<Person> find = personMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testShoudExecuteWhenFirstNameIsNull() {
        PersonSearch search = new PersonSearch();
        search.setLastName("last_name_page");
        List<Person> find = personMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenFirstNameIsNullStr() {
        PersonSearch search = new PersonSearch();
        search.setFirstName("");
        search.setLastName("last_name_page");
        List<Person> find = personMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenLastNameIsNull() {
        PersonSearch search = new PersonSearch();
        search.setFirstName("first_name_page");
        List<Person> find = personMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenLastNameIsNullStr() {
        PersonSearch search = new PersonSearch();
        search.setFirstName("first_name_page");
        List<Person> find = personMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        PersonSearch search = new PersonSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        Long count = personMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        PersonSearch search = new PersonSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        search.setLow(0);
        search.setHigh(20);
        List<Person> listPerson = personMapper.page(search);
        assertEquals(3, listPerson.size());
    }

}
