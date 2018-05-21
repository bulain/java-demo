package com.bulain.jcr;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.resource.ResourceException;
import javax.resource.spi.ManagedConnectionFactory;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.test.ServiceTestCase;

public class JacTest extends ServiceTestCase {
    @Autowired
    private ManagedConnectionFactory jcaConnectionFactory;
    @Autowired
    private Repository jcrRepository;
    
    @Test
    public void testConnectionFactory() throws ResourceException, LoginException, RepositoryException{
        assertNotNull(jcaConnectionFactory);
        
        Repository repository = (Repository) jcaConnectionFactory.createConnectionFactory();
        assertNotNull(repository);
        
        Session session = repository.login(new SimpleCredentials("username", "password".toCharArray()));
        
        String user = session.getUserID();
        String name = repository.getDescriptor(Repository.REP_NAME_DESC);

        assertEquals("username", user);
        assertEquals("Jackrabbit", name);

        System.out.println("Logged in as " + user + " to a " + name + " repository.");
    }
    
    @Test
    public void testConnection() throws LoginException, RepositoryException{
        assertNotNull(jcrRepository);
        
        Session session = jcrRepository.login(new SimpleCredentials("username", "password".toCharArray()));
        
        String user = session.getUserID();
        String name = jcrRepository.getDescriptor(Repository.REP_NAME_DESC);

        assertEquals("username", user);
        assertEquals("Jackrabbit", name);

        System.out.println("Logged in as " + user + " to a " + name + " repository.");
    }
}
