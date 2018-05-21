package com.bulain.hibernate.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
    private static final String UTF_8 = "UTF-8";
    private static final String TEMPLATE_NAME = "hql";
    
    public static String formatHQL(String hql, Object rootMap) throws IOException, TemplateException {
        Configuration cfg = new Configuration();
        StringTemplateLoader loader = new StringTemplateLoader();
        loader.putTemplate(TEMPLATE_NAME, hql);
        cfg.setTemplateLoader(loader);
        cfg.setDefaultEncoding(UTF_8);
        Template template = cfg.getTemplate(TEMPLATE_NAME);
        Writer out = new StringWriter();
        template.process(rootMap, out);
        return out.toString();
    }
}
