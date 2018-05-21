package com.bulain.jmx.demo;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class JmxServerIT {
    static ClassPathXmlApplicationContext applicationContext;

    public static void main(String[] args) {
        try {
            applicationContext = new ClassPathXmlApplicationContext(new String[]{
                    "classpath:spring/applicationContext-resource.xml", "classpath:spring/applicationContext-jmx.xml"});

            System.out.println("Click any key to exit...");
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
