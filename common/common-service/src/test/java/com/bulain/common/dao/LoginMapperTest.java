package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Login;
import com.bulain.common.pojo.LoginSearch;

@DataSet(file = "test-data/init_logins.xml")
public class LoginMapperTest extends ServiceTestCase {
    @Autowired
    private LoginMapper loginMapper;

    @Test
    public void testCountLoginByGroupId() {
        Long countLoginByGroupId = loginMapper.countLoginByGroupId(Long.valueOf(105));
        assertEquals(Long.valueOf(3), countLoginByGroupId);
    }

    @Test
    public void testFindLoginByGroupId() {
        List<Login> findLoginByGroupId = loginMapper.findLoginByGroupId(Long.valueOf(105));
        assertEquals(3, findLoginByGroupId.size());
    }

    @Test
    public void testFindLoginByNoGroupId() {
        List<Login> findLoginByNoGroupId = loginMapper.findLoginByNoGroupId(Long.valueOf(105));
        assertEquals(4, findLoginByNoGroupId.size());
    }

    @Test
    public void testFindLoginByLoginNames() {
        String[] loginNames = new String[]{"login_name_page"};
        List<Login> findLoginByLoginNames = loginMapper.findLoginByLoginNames(loginNames);
        assertEquals(3, findLoginByLoginNames.size());
    }

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = loginMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Login record = new Login();
        record.setLoginName("loginName");
        record.setEmail("email@email.com");
        record.setHashedPassword("hashedPassword");
        record.setEnabled("enabled");
        int insert = loginMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Login record = new Login();
        record.setLoginName("loginName");
        record.setEmail("email@email.com");
        record.setHashedPassword("hashedPassword");
        record.setEnabled("enabled");
        int insertSelective = loginMapper.insertSelective(record);
        assertEquals(1, insertSelective);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Login select = loginMapper.selectByPrimaryKey(Long.valueOf(102));
        assertEquals("login_name_102", select.getLoginName());
        assertEquals("email_102", select.getEmail());
        assertEquals("hashed_password_102", select.getHashedPassword());
        assertEquals("enabled_102", select.getEnabled());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Login record = new Login();
        record.setId(Long.valueOf(103));
        record.setLoginName("loginName-updated");
        record.setEmail("email@email.com-updated");
        record.setHashedPassword("hashedPassword-updated");
        record.setEnabled("enabled-updated");
        int updateByPrimaryKeySelective = loginMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Login record = new Login();
        record.setId(Long.valueOf(103));
        record.setLoginName("loginName-updated");
        record.setEmail("email@email.com-updated");
        record.setHashedPassword("hashedPassword-updated");
        record.setEnabled("enabled-updated");
        int updateByPrimaryKey = loginMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        LoginSearch search = new LoginSearch();
        search.setLoginName("login_name_page");
        search.setEmail("email_page");
        search.setEnabled("enabled_page");
        List<Login> find = loginMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        LoginSearch search = new LoginSearch();
        search.setLoginName("login_name_page");
        search.setEmail("email_page");
        search.setEnabled("enabled_page");
        Long count = loginMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        LoginSearch search = new LoginSearch();
        search.setLoginName("login_name_page");
        search.setEmail("email_page");
        search.setEnabled("enabled_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<Login> page2 = loginMapper.page(search);
        assertEquals(3, page2.size());
    }

}
