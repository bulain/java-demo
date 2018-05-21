package com.bulain.activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.activiti.model.Account;
import com.bulain.activiti.pojo.AccountSearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;

@DataSet(file = "test-data/init_accounts.xml")
public class AccountServiceTest extends ServiceTestCase {
    @Autowired
    @Qualifier("accountServiceTx")
    private AccountService accountService;

    @Test
    public void testGet() {
        Account account = accountService.get(Long.valueOf(102));
        assertNotNull(account);

        assertEquals("name_102", account.getName());
        assertEquals("type_102", account.getType());
    }

    @Test
    public void testInsert() {
        Account account = new Account();
        account.setName("name");
        account.setType("type");
        accountService.insert(account);
    }

    @Test
    public void testUpdate() {
        Account account = new Account();
        account.setId(Long.valueOf(104));
        account.setName("name-updated");
        account.setType("type-updated");
        accountService.update(account, true);
    }

    @Test
    public void testDelete() {
        accountService.delete(Long.valueOf(101));
    }

    @Test
    public void testCountByUniqueKey4Create() {
        Account record = new Account();
        record.setName("name_104");
        Long cnt = accountService.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testCountByUniqueKey4Update() {
        Account record = new Account();
        record.setId(Long.valueOf(104));
        record.setName("name_103");
        Long cnt = accountService.countByDuplicate(record);
        assertEquals(Long.valueOf(1), cnt);
    }

    @Test
    public void testFind() {
        AccountSearch search = new AccountSearch();
        search.setName("name_page");
        search.setType("type_page");
        List<Account> find = accountService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        AccountSearch search = new AccountSearch();
        search.setName("name_page");
        search.setType("type_page");
        Long count = accountService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        AccountSearch search = new AccountSearch();
        search.setName("name_page");
        search.setType("type_page");
        Page page = new Page();
        List<Account> listAccount = accountService.page(search, page);
        assertEquals(3, listAccount.size());
    }

}
