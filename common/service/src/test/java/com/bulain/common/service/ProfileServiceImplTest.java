package com.bulain.common.service;

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
public class ProfileServiceImplTest extends ServiceTestCase {
    @Autowired
    private ProfileService profileService;

    @Test
    public void testFind() {
        ProfileSearch search = new ProfileSearch();
        search.setPersonId(Long.valueOf(105));
        search.setLanguage("language_page");
        search.setCountry("country_page");
        List<Profile> find = profileService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        ProfileSearch search = new ProfileSearch();
        search.setPersonId(Long.valueOf(105));
        search.setLanguage("language_page");
        search.setCountry("country_page");
        Long count = profileService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        ProfileSearch search = new ProfileSearch();
        search.setPersonId(Long.valueOf(105));
        search.setLanguage("language_page");
        search.setCountry("country_page");
        Page page = new Page();
        List<Profile> page2 = profileService.page(search, page);
        assertEquals(3, page2.size());
    }

    @Test
    public void testGet() {
        Profile profile = profileService.get(Long.valueOf(102));
        assertNotNull(profile);
        assertEquals(Long.valueOf(102), profile.getPersonId());
        assertEquals("language_102", profile.getLanguage());
        assertEquals("country_102", profile.getCountry());
    }

    @Test
    public void testInsert() {
        Profile record = new Profile();
        record.setPersonId(Long.valueOf(108));
        record.setLanguage("language");
        record.setCountry("country");
        profileService.insert(record);
    }

    @Test
    public void testUpdate() {
        Profile record = new Profile();
        record.setId(Long.valueOf(103));
        record.setPersonId(Long.valueOf(103));
        record.setLanguage("language-updated");
        record.setCountry("country-updated");
        profileService.update(record, true);
    }

    @Test
    public void testDelete() {
        profileService.delete(Long.valueOf(101));
    }

}
