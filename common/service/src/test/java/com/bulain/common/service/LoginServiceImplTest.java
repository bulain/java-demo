package com.bulain.common.service;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Login;
import com.bulain.common.pojo.LoginSearch;

@DataSet(file = "test-data/init_logins.xml")
public class LoginServiceImplTest extends ServiceTestCase {
    @Autowired
    private LoginService loginService;

    @Test
    public void testCountLoginByGroupId() {
        Long countLoginByGroupId = loginService.countLoginByGroupId(Long.valueOf(105));
        assertEquals(Long.valueOf(3), countLoginByGroupId);
    }

    @Test
    public void testFindLoginByGroupId() {
        List<Login> findLoginByGroupId = loginService.findLoginByGroupId(Long.valueOf(105));
        assertEquals(3, findLoginByGroupId.size());
    }

    @Test
    public void testFindLoginByNoInGroupId() {
        List<Login> findLoginByNoInGroupId = loginService.findLoginByNoInGroupId(Long.valueOf(105));
        assertEquals(4, findLoginByNoInGroupId.size());
    }

    @Test
    public void testUpdateGroupLogin() {
        Long loginId = Long.valueOf(102);
        List<Long> listGroupId = Arrays.asList(new Long[]{102L, 105L});
        loginService.updateGroupLogin(loginId, listGroupId);
    }

    @Test
    public void testGetLogin() {
        Login login = loginService.getLogin("login_name_102", "hashed_password_102");
        assertEquals("login_name_102", login.getLoginName());
        assertEquals("email_102", login.getEmail());
        assertEquals("hashed_password_102", login.getHashedPassword());
        assertEquals("enabled_102", login.getEnabled());
    }

    @Test
    public void testFindPermission() {
        List<String> findPermission = loginService.findPermission(Long.valueOf(102));
        assertEquals(1, findPermission.size());
    }

    @Test
    public void testFind() {
        LoginSearch search = new LoginSearch();
        search.setLoginName("login_name_page");
        search.setEmail("email_page");
        search.setEnabled("enabled_page");
        List<Login> find = loginService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        LoginSearch search = new LoginSearch();
        search.setLoginName("login_name_page");
        search.setEmail("email_page");
        search.setEnabled("enabled_page");
        Long count = loginService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        LoginSearch search = new LoginSearch();
        search.setLoginName("login_name_page");
        search.setEmail("email_page");
        search.setEnabled("enabled_page");
        Page page = new Page();
        List<Login> page2 = loginService.page(search, page);
        assertEquals(3, page2.size());
    }

    @Test
    public void testGet() {
        Login login = loginService.get(Long.valueOf(102));
        assertEquals("login_name_102", login.getLoginName());
        assertEquals("email_102", login.getEmail());
        assertEquals("hashed_password_102", login.getHashedPassword());
        assertEquals("enabled_102", login.getEnabled());
    }

    @Test
    public void testInsert() {
        Login record = new Login();
        record.setLoginName("loginName");
        record.setEmail("email@email.com");
        record.setHashedPassword("hashedPassword");
        record.setEnabled("enabled");
        loginService.insert(record);
    }

    @Test
    public void testUpdate() {
        Login record = new Login();
        record.setId(Long.valueOf(103));
        record.setLoginName("loginName-updated");
        record.setEmail("email@email.com-updated");
        record.setHashedPassword("hashedPassword-updated");
        record.setEnabled("enabled-updated");
        loginService.update(record, true);
    }

    @Test
    public void testDelete() {
        loginService.delete(Long.valueOf(101));
    }

}
