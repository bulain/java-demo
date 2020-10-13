package com.bulain.common.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class JQueryUtilTest {

    @Test
    public void testGetJqueryContent() {
        String jqueryContent = JQueryUtil.getJqueryContent();
        assertNotNull(jqueryContent);
    }

}
