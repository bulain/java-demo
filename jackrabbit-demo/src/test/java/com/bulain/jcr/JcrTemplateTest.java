package com.bulain.jcr;

import static org.junit.Assert.assertNotNull;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springmodules.jcr.JcrTemplate;

import com.bulain.common.test.ServiceTestCase;

public class JcrTemplateTest extends ServiceTestCase {
    @Autowired
    private JcrTemplate jcrTemplate;

    @Test
    public void testJcrTemplate() throws RepositoryException {
        Node root = jcrTemplate.getRootNode();
        assertNotNull(root);
    }
}
