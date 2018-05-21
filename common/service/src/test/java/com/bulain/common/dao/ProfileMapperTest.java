package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Profile;
import com.bulain.common.pojo.ProfileSearch;

@DataSet(file = "test-data/init_profiles.xml")
public class ProfileMapperTest extends ServiceTestCase {
    @Autowired
    private ProfileMapper profileMapper;

    @Test
    public void testFind() {
        ProfileSearch search = new ProfileSearch();
        search.setPersonId(Long.valueOf(105));
        search.setLanguage("language_page");
        search.setCountry("country_page");
        List<Profile> find = profileMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        ProfileSearch search = new ProfileSearch();
        search.setPersonId(Long.valueOf(105));
        search.setLanguage("language_page");
        search.setCountry("country_page");
        Long count = profileMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        ProfileSearch search = new ProfileSearch();
        search.setPersonId(Long.valueOf(105));
        search.setLanguage("language_page");
        search.setCountry("country_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<Profile> page2 = profileMapper.page(search);
        assertEquals(3, page2.size());
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = profileMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Profile record = new Profile();
        record.setPersonId(Long.valueOf(108));
        record.setLanguage("language");
        record.setCountry("country");
        int insert = profileMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Profile record = new Profile();
        record.setPersonId(Long.valueOf(108));
        record.setLanguage("language");
        record.setCountry("country");
        int insertSelective = profileMapper.insertSelective(record);
        assertEquals(1, insertSelective);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Profile selectByPrimaryKey = profileMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);
        assertEquals(Long.valueOf(102), selectByPrimaryKey.getPersonId());
        assertEquals("language_102", selectByPrimaryKey.getLanguage());
        assertEquals("country_102", selectByPrimaryKey.getCountry());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Profile record = new Profile();
        record.setId(Long.valueOf(103));
        record.setPersonId(Long.valueOf(103));
        record.setLanguage("language-updated");
        record.setCountry("country-updated");
        int updateByPrimaryKeySelective = profileMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Profile record = new Profile();
        record.setId(Long.valueOf(104));
        record.setPersonId(Long.valueOf(104));
        record.setLanguage("language-updated");
        record.setCountry("country-updated");
        int updateByPrimaryKey = profileMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

}
