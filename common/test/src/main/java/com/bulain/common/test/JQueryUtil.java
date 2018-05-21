package com.bulain.common.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class JQueryUtil {
    private static final Logger logger = LoggerFactory.getLogger(JQueryUtil.class);

    private static final String JQUERY_PATH = "js/jquery-1.8.2.min.js";
    private static String jqueryContent;

    public static String getJqueryContent() {
        if (jqueryContent == null) {
            initJqueryContent();
        }
        return jqueryContent;
    }

    private static void initJqueryContent() {
        ClassPathResource resource = new ClassPathResource(JQUERY_PATH);
        try {
            InputStream inputStream = resource.getInputStream();
            jqueryContent = IOUtils.toString(inputStream);
            IOUtils.closeQuietly(inputStream);
        } catch (IOException e) {
            logger.error("initJqueryContent()", e);
        }
    }

}
