package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.hibernate.entity.Person;

@SeedDataSet(file = "test-data/init_seed_dataset.xml")
public class VersionTest extends ServiceTestCase {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testVersion() {
        Person person = new Person();
        person.setFirstName("firstName");
        person.setLastName("lastName");
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(person);
        tx.commit();
        session.close();
        Integer version = person.getVersion();
        assertEquals(Integer.valueOf(0), version);
        
        session = sessionFactory.openSession();
        person = (Person) session.get(Person.class, person.getId());
        session.close();
        person.setFirstName("firstName-updated");
        person.setLastName("lastName-updated");
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        session.update(person);
        tx.commit();
        session.close();
        version = person.getVersion();
        assertEquals(Integer.valueOf(1), version);
        
        session = sessionFactory.openSession();
        person = (Person) session.get(Person.class, person.getId());
        session.close();
        person.setFirstName("firstName-again");
        person.setLastName("lastName-again");
        person.setVersion(100);
        session = sessionFactory.openSession();
        tx = session.beginTransaction();
        try {
            session.update(person);
            tx.commit();
            fail("Optimistic lock check fail");
        } catch (StaleObjectStateException e) {
            tx.rollback();
        }
        session.close();
    }
}
