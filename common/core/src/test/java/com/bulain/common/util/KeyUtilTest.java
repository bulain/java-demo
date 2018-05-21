package com.bulain.common.util;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class KeyUtilTest {

    @Test
    public void testGetSessionKey() {
        String sessionKey = KeyUtil.getSessionKey(KeyUtilTest.class, KeyUtilTest.class);
        assertEquals("KeyUtilTest_KeyUtilTest", sessionKey);
    }

    @Test
    public void testGetCacheKey() {
        String cacheKey = KeyUtil.getCacheKey(KeyUtilTest.class);
        assertEquals("KeyUtilTest", cacheKey);
    }

    @Test
    public void testGetShortClassName() {
        String shortClassName = KeyUtil.getShortClassName(KeyUtilTest.class);
        assertEquals("KeyUtilTest", shortClassName);
    }
}
