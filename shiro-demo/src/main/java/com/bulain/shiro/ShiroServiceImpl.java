package com.bulain.shiro;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShiroServiceImpl implements ShiroService {
    private static Logger logger = LoggerFactory.getLogger(ShiroServiceImpl.class);
    
    public void callByAuth() {
        logger.info("callByAuth()");
    }

    public void callByRole() {
        logger.info("callByRole()");
    }

    public void callByPerm() {
        logger.info("callByPerm()");
    }

}
