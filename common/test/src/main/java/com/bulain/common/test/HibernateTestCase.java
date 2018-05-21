package com.bulain.common.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class HibernateTestCase extends DaoTestCase {
    @Autowired
    private SessionFactory sessionFactory;
    protected Session session;
    
    @Before
    public void setUp() {
        session = sessionFactory.getCurrentSession();
    }
    
    @After
    public void tearDown() {
        session.flush();
    }
}
