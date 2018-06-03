package com.bulain.cxf.soap;

import java.util.List;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.headers.Header;
import org.apache.cxf.helpers.DOMUtils;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class MySoapHeader extends AbstractSoapInterceptor {

    private String nameURI = "http://tempuri.org/";

    private String username;
    private String password;

    public MySoapHeader() {
        super(Phase.WRITE);
    }

    public void handleMessage(SoapMessage message) throws Fault {

        QName qname = new QName("MySoapHeader");

        Document doc = DOMUtils.createDocument();
        Element eUsername = doc.createElement("UserName");
        eUsername.setTextContent(username);
        Element ePassword = doc.createElement("PassWord");
        ePassword.setTextContent(password);

        Element root = doc.createElementNS(nameURI, "MySoapHeader");
        root.appendChild(eUsername);
        root.appendChild(ePassword);

        SoapHeader head = new SoapHeader(qname, root);
        List<Header> headers = message.getHeaders();
        headers.add(head);

    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
