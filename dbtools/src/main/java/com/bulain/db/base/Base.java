package com.bulain.db.base;

import org.springframework.context.ApplicationContext;
import org.springframework.test.context.support.GenericXmlContextLoader;
import org.springframework.util.StringUtils;

public class Base {
    private static final String CONFIG_LOCATION_DELIMITERS = ",; \t\n";
    private static final String DEFAULT_CONTEXT_LOCATION = "classpath*:spring/applicationContext*.xml, classpath*:spring/propertyConfigurer.xml";

    protected static ApplicationContext applicationContext;

    protected String getContextLocations() {
        return DEFAULT_CONTEXT_LOCATION;
    }

    protected synchronized void setupBeforeInitDispatcher() throws Exception {
        applicationContext = ContextHolder.getApplicationContext();
        if (applicationContext == null) {
            GenericXmlContextLoader xmlContextLoader = new GenericXmlContextLoader();
            String[] contextLocations = StringUtils.tokenizeToStringArray(getContextLocations(),
                    CONFIG_LOCATION_DELIMITERS);
            applicationContext = xmlContextLoader.loadContext(contextLocations);
            ContextHolder.setApplicationContext(applicationContext);
        }
    }
}
