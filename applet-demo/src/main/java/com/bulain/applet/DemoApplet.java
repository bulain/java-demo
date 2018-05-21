package com.bulain.applet;

import java.applet.Applet;
import java.util.logging.Logger;

import com.bulain.log.InitJdkLog;

public class DemoApplet extends Applet {
    private static final long serialVersionUID = -5412173979481024344L;

    protected static InitJdkLog initJdkLog = new InitJdkLog();
    private static Logger logger = Logger.getLogger("DemoApplet");

    public void init() {
        logger.info("init");
    }

    public void destroy() {
        logger.info("destroy");
    }

    public void start() {
        logger.info("start");
    }

    public void stop() {
        logger.info("stop");
    }

}
