package com.bulain.log;

import java.util.logging.Logger;

public class JdkLog {
    private static Logger logger = Logger.getLogger("JdkLog");
    
    public void test() {
        logger.info("init -- start");
        
        logger.info("init -- end");
    }
    
}
