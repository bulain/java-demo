package com.bulain.zip;

import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipTest {

    @Test
    public void testEnglish() throws IOException {
        String src = "src/test/resources/test-data/english.zip";
        String dir = "target/";
        unzip(src, dir);
    }

    @Test
    public void testChinese() throws FileNotFoundException, IOException {
        String src = "src/test/resources/test-data/中文测试.zip";
        String dir = "target/";
        unzip(src, dir);
    }

    private void unzip(String src, String dir) throws IOException, FileNotFoundException {
        
        File file = new File(dir);
        if(!file.exists()){
            file.mkdirs();
        }
        
        ZipFile zip = new ZipFile(src, Charset.forName("GBK"));
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();

            System.out.println(entry.getName());
            
            String name = entry.getName();
            if(name.endsWith("/")){
                file = new File(dir + name );
                if(!file.exists()){
                    file.mkdirs();
                }
                continue;
            }
            
            String dest = dir + name;

            BufferedInputStream bin = new BufferedInputStream(zip.getInputStream(entry));
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest));
            try {
                IOUtils.copy(bin, bos);
            } finally {
                IOUtils.closeQuietly(bin);
                IOUtils.closeQuietly(bos);
            }
        }
    }

}
