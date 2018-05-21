package com.bulain.cassandra;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.bulain.cassandra.pojo.Account;
import com.bulain.cassandra.pojo.Address;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

public class MapperDemo extends CassTestCase{

    @Test
    public void testMapper() {
        Mapper<Account> mapper = new MappingManager(session).mapper(Account.class);
        List<String> phones = new ArrayList<String>();
        phones.add("707-555-3537");
        phones.add("123-444-5643");
        Address address = new Address("25800 Arnold Drive", "Sonoma", 95476, phones);
        Account account = new Account("John Doe", "jd@example.com", address);
        mapper.save(account);
        Account whose = mapper.get("jd@example.com");
        System.out.println("Account name: " + whose.getName());
        System.out.println("Email: " + whose.getEmail());
        System.out.println("Address: " + whose.getAddress());
        mapper.delete(account);
    }
    
}
