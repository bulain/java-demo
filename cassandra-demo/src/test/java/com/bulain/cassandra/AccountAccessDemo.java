package com.bulain.cassandra;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.bulain.cassandra.dao.AccountAccessor;
import com.bulain.cassandra.pojo.Account;
import com.bulain.cassandra.pojo.Address;
import com.datastax.driver.mapping.MappingManager;
import com.datastax.driver.mapping.Result;

public class AccountAccessDemo extends CassTestCase {
    private AccountAccessor accessor;

    @Before
    public void setUp() {
        MappingManager manager = new MappingManager(session);
        accessor = manager.createAccessor(AccountAccessor.class);
    }

    @Test
    public void testAccess() {
        //insert
        {
            List<String> phones = new ArrayList<String>();
            phones.add("707-555-3537");
            phones.add("123-444-5643");
            Address addr = new Address("25800 Arnold Drive", "Sonoma", 95476, phones);
            accessor.insert("test@g.com", "test", addr);
        }

        //find
        {
            Result<Account> accounts = accessor.find("Sonoma");
            for (Account account : accounts) {
                System.out.println(account.getEmail());
            }
        }

        //update
        {
            List<String> phones = new ArrayList<String>();
            phones.add("707-222-3537");
            phones.add("123-111-5643");
            Address addr = new Address("25800 Arnold Drive", "Sonoma", 95476, phones);
            accessor.update("test2@g.com", "test2", addr);
        }

        //get
        {
            Account account = accessor.get("test@g.com");
            System.out.println(account.getEmail());
        }

        //delete
        accessor.delete("test@g.com");

    }

}
