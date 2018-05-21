package com.bulain.common.util;

import static org.junit.Assert.*;
import org.junit.Test;

public class SqlUtilTest {
    private static final String COLUMN_PATTERN = "^[a-zA-Z_0-9]*$";
    
    @Test
    public void testSqlInject(){
        assertTrue("abc".matches(COLUMN_PATTERN));
        assertTrue("ABC".matches(COLUMN_PATTERN));
        assertTrue("Abc".matches(COLUMN_PATTERN));
        assertTrue("aBc".matches(COLUMN_PATTERN));
        assertTrue("a_b".matches(COLUMN_PATTERN));
        assertTrue("a_0".matches(COLUMN_PATTERN));
        
        
        assertFalse("9+7".matches(COLUMN_PATTERN));
        assertFalse("9-7".matches(COLUMN_PATTERN));
        assertFalse("9*7".matches(COLUMN_PATTERN));
        assertFalse("9/7".matches(COLUMN_PATTERN));
        assertFalse("a b".matches(COLUMN_PATTERN));
        assertFalse("a$".matches(COLUMN_PATTERN));
        
    }
}
