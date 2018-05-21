package com.bulain.activiti.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.activiti.model.Account;
import com.bulain.activiti.pojo.AccountSearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.DaoTestCase;

@DataSet(file = "test-data/init_accounts.xml")
public class AccountMapperTest extends DaoTestCase {
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = accountMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Account record = new Account();
        record.setName("name");
        record.setType("type");
        int insert = accountMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Account record = new Account();
        record.setName("name");
        record.setType("type");
        int insert = accountMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Account selectByPrimaryKey = accountMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);

        assertEquals("name_102", selectByPrimaryKey.getName());
        assertEquals("type_102", selectByPrimaryKey.getType());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Account record = new Account();
        record.setId(Long.valueOf(103));
        record.setName("name-updated");
        record.setType("type-updated");
        int updateByPrimaryKeySelective = accountMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Account record = new Account();
        record.setId(Long.valueOf(104));
        record.setName("name-updated");
        record.setType("type-updated");
        int updateByPrimaryKey = accountMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testCountByUniqueKey4Create() {
        Account record = new Account();
        record.setName("name_104");
        Long cnt = accountMapper.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testCountByUniqueKey4Update() {
        Account record = new Account();
        record.setId(Long.valueOf(104));
        record.setName("name_103");
        Long cnt = accountMapper.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testFind() {
        AccountSearch search = new AccountSearch();
        search.setName("name_page");
        List<Account> find = accountMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testShoudExecuteWhenNoParam() {
        AccountSearch search = new AccountSearch();
        List<Account> find = accountMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testShoudExecuteWhenFirstNameIsNullStr() {
        AccountSearch search = new AccountSearch();
        search.setName("");
        List<Account> find = accountMapper.find(search);
        assertEquals(7, find.size());
    }

    @Test
    public void testCount() {
        AccountSearch search = new AccountSearch();
        search.setName("name_page");
        Long count = accountMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        AccountSearch search = new AccountSearch();
        search.setName("name_page");
        search.setLow(0);
        search.setHigh(20);
        List<Account> listAccount = accountMapper.page(search);
        assertEquals(3, listAccount.size());
    }

}
