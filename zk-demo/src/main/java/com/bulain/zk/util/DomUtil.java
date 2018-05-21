package com.bulain.zk.util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class DomUtil {
    public static Document parse(InputSource in) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            return db.parse(in);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
