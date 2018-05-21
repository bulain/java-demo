package com.bulain.jolokia.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServerConnection;
import javax.management.ObjectInstance;
import javax.management.ObjectName;
import javax.management.OperationsException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath:spring/applicationContext-resource.xml",
        "classpath:spring/jmx-client.xml"})
public class JmxClientIT {

    @Autowired
    private MBeanServerConnection clientConnector;

    @Test
    public void testGetObjectInstance() throws OperationsException, IOException {
        ObjectName name = new ObjectName("jolokia:name=JmxBean");
        ObjectInstance objectInstance = clientConnector.getObjectInstance(name);
        assertNotNull(objectInstance);
    }

    @Test
    public void testQueryMBeans() throws OperationsException, IOException {
        ObjectName name = new ObjectName("*:*");

        Set<ObjectInstance> queryMBeans = clientConnector.queryMBeans(name, name);
        List<String> names = new ArrayList<String>();
        for (ObjectInstance oi : queryMBeans) {
            names.add(oi.getObjectName().getCanonicalName());
        }

        assertTrue(names.contains("jolokia:name=JmxBean"));
    }

    @Test
    public void testGetDefaultDomain() throws IOException {
        String defaultDomain = clientConnector.getDefaultDomain();
        assertNotNull(defaultDomain);
        assertEquals("DefaultDomain", defaultDomain);
    }

    @Test
    public void testGetDomains() throws IOException {
        String[] domains = clientConnector.getDomains();
        List<String> listDomains = Arrays.asList(domains);
        assertTrue(listDomains.contains("jolokia"));
    }

}
