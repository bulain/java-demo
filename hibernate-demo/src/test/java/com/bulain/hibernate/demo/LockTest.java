package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.hibernate.LockMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.hibernate.entity.Person;
import com.bulain.hibernate.entity.User;

public class LockTest extends ServiceTestCase {
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    @DataSet(file = "test-data/init_users.xml")
    public void testPessimistic() throws InterruptedException, ExecutionException, TimeoutException {
        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Callable<User> worker1 = new PessimisticWorker(session1, 101L);
        Callable<User> worker2 = new PessimisticWorker(session2, 101L);
        
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<User> future1 = executorService.submit(worker1);
        Future<User> future2 = executorService.submit(worker2);
        
        User user1 = future1.get(1L, TimeUnit.MINUTES);
        User user2 = future2.get(1L, TimeUnit.MINUTES);
        assertEquals(Long.valueOf(101), user1.getId());
        assertEquals(Long.valueOf(101), user2.getId());
        assertNotSame(user1.getFirstName(), user2.getFirstName());
        
        session1.close();
        session2.close();
    }
    
    @Test
    @DataSet(file = "test-data/init_persons.xml")
    public void testPessimisticReflush() throws InterruptedException, ExecutionException, TimeoutException {
        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        Callable<Person> worker1 = new PessimisticReflushWorker(session1, 101L);
        Callable<Person> worker2 = new PessimisticReflushWorker(session2, 101L);
        
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<Person> future1 = executorService.submit(worker1);
        Future<Person> future2 = executorService.submit(worker2);
        
        Person person1 = future1.get(1L, TimeUnit.MINUTES);
        Person person2 = future2.get(1L, TimeUnit.MINUTES);
        assertEquals(Long.valueOf(101), person1.getId());
        assertEquals(Long.valueOf(101), person2.getId());
        assertNotSame(person1.getFirstName(), person2.getFirstName());
        
        session1.close();
        session2.close();
    }
    
    static class PessimisticWorker implements Callable<User> {
        private Session session;
        private Long id;
        
        public PessimisticWorker(Session session, Long id) {
            this.session = session;
            this.id = id;
        }
        
        public User call() throws Exception {
            Transaction tx = session.beginTransaction();
            User user = (User) session.get(User.class, id, LockMode.UPGRADE);
            long threadId = Thread.currentThread().getId();
            user.setFirstName("first_name-" + threadId);
            session.save(user);
            session.flush();
            tx.commit();
            return user;
        }
    }
    
    static class PessimisticReflushWorker implements Callable<Person> {
        private Session session;
        private Long id;
        
        public PessimisticReflushWorker(Session session, Long id) {
            this.session = session;
            this.id = id;
        }
        
        public Person call() throws Exception {
            Transaction tx = session.beginTransaction();
            Person person = (Person) session.get(Person.class, id);
            session.refresh(person, LockMode.UPGRADE);
            long threadId = Thread.currentThread().getId();
            person.setFirstName("first_name-" + threadId);
            session.save(person);
            session.flush();
            tx.commit();
            return person;
        }
    }
    
}
