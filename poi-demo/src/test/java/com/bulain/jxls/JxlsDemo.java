package com.bulain.jxls;

import org.junit.Test;
import org.jxls.common.Context;
import org.jxls.util.JxlsHelper;
import org.springframework.core.io.ClassPathResource;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class JxlsDemo {

    @Test
    public void testExport() throws IOException {

        List<JxlsBean> dts = new ArrayList<>();
        dts.add(new JxlsBean(1001, "name-1", "descr-1"));
        dts.add(new JxlsBean(1002, "name-2", "descr-2"));
        
        try (InputStream is = new ClassPathResource("jxls/jxls_template.xlsx").getInputStream()) {
            try (OutputStream os = new FileOutputStream("target/jxls_output.xlsx")) {
                Context context = new Context();
                
                context.putVar("dts", dts);
                JxlsHelper.getInstance().processTemplate(is, os, context);
            }
        }

    }

}
