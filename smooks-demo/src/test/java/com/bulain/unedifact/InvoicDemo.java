package com.bulain.unedifact;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Test;
import org.milyn.Smooks;
import org.milyn.SmooksException;
import org.milyn.edi.unedifact.d93a.D93AInterchangeFactory;
import org.milyn.edi.unedifact.d93a.INVOIC.Invoic;
import org.milyn.edisax.model.internal.Delimiters;
import org.milyn.smooks.edi.unedifact.UNEdifactReaderConfigurator;
import org.milyn.smooks.edi.unedifact.model.UNEdifactInterchange;
import org.milyn.smooks.edi.unedifact.model.r41.UNB41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactInterchange41;
import org.milyn.smooks.edi.unedifact.model.r41.UNEdifactMessage41;
import org.milyn.smooks.edi.unedifact.model.r41.UNZ41;
import org.milyn.smooks.edi.unedifact.model.r41.types.DateTime;
import org.milyn.smooks.edi.unedifact.model.r41.types.Party;
import org.xml.sax.SAXException;

public class InvoicDemo {

    @Test
    public void testWriter() throws IOException, SAXException {

        UNEdifactInterchange41 interchange = new UNEdifactInterchange41();
        Delimiters interchangeDelimiters = null;
        UNB41 interchangeHeader = new UNB41();
        UNZ41 interchangeTrailer = new UNZ41();
        List<UNEdifactMessage41> messages = new ArrayList<UNEdifactMessage41>();

        interchangeHeader.setAckRequest("x");
        interchangeHeader.setAgreementId("w");
        interchangeHeader.setControlRef("v");
        Party sender = new Party();
        sender.setCodeQualifier("w");
        sender.setId("b");
        interchangeHeader.setSender(sender);
        DateTime date = new DateTime();
        date.setDate("20160204");
        interchangeHeader.setDate(date);

        interchangeTrailer.setControlCount(2);
        interchangeTrailer.setControlRef("y");

        Invoic invoic = new Invoic();
        UNEdifactMessage41 m1 = new UNEdifactMessage41();
        m1.setInterchangeHeader(interchangeHeader);
        m1.setMessage(invoic);
        messages.add(m1);

        interchange.setInterchangeDelimiters(interchangeDelimiters);
        interchange.setInterchangeHeader(interchangeHeader);
        interchange.setInterchangeTrailer(interchangeTrailer);
        interchange.setMessages(messages);

        D93AInterchangeFactory factory = D93AInterchangeFactory.getInstance();
        Writer writer = new OutputStreamWriter(System.out);
        factory.toUNEdifact(interchange, writer);
    }

    @Test
    public void testReader() throws IOException, SAXException {
        D93AInterchangeFactory factory = D93AInterchangeFactory.getInstance();
        InputStream ediSource = new FileInputStream("src/test/resources/test-data/invoic.edi");
        UNEdifactInterchange fromUNEdifact = factory.fromUNEdifact(ediSource);

        if (fromUNEdifact instanceof UNEdifactInterchange41) {
            UNEdifactInterchange41 interchange41 = (UNEdifactInterchange41) fromUNEdifact;
            Delimiters interchangeDelimiters = interchange41.getInterchangeDelimiters();
            UNB41 interchangeHeader = interchange41.getInterchangeHeader();
            UNZ41 interchangeTrailer = interchange41.getInterchangeTrailer();
            List<UNEdifactMessage41> messages = interchange41.getMessages();

            System.out.println(interchangeDelimiters);
            System.out.println(interchangeHeader);
            System.out.println(interchangeTrailer);
            System.out.println(messages);
        }
    }

    @Test
    public void testXml() throws SmooksException, IOException, SAXException {
        //Smooks smooks = new Smooks("smooks-config.xml");
        Smooks smooks = new Smooks();
        smooks.setReaderConfig(new UNEdifactReaderConfigurator("urn:org.milyn.edi.unedifact:d93a-mapping:*"));
        StringWriter writer = new StringWriter();
        smooks.filterSource(new StreamSource(new FileInputStream("src/test/resources/test-data/invoic.edi")), new StreamResult(writer));
        String xml = writer.toString();
        System.out.println(xml);

    }

}
