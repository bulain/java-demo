package com.bulain.log;

import java.io.IOException;

import org.junit.Test;

//-Djava.util.logging.config.file=src/main/resources/logging.properties
//-Djava.util.logging.config.class=com.bulain.log.InitJdkLog
public class JdkLogTest {
    @Test
    public void testTest() throws SecurityException, IOException {
        JdkLog jdkLog = new JdkLog();    
        jdkLog.test();
    }

}
