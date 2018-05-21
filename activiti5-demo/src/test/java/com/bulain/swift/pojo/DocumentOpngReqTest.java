package com.bulain.swift.pojo;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.SAXException;

import com.bulain.swift.service.XMLStreamReaderImpl;
import com.bulain.swift.service.XMLStreamWriterImpl;

public class DocumentOpngReqTest {
    @Test
    public void testUnmarshal() throws IOException, JAXBException, XMLStreamException, FactoryConfigurationError {
        // get test file
        File file = getTestFile("acmt/acmt.007.001.01.xml");

        // unmarshal
        Reader reader = new FileReader(file);
        DocumentOpngReq document = unmarshal(reader);

        // assert
        assertNotNull(document);
        assertNotNull(document.getAcctOpngReq());
    }

    private DocumentOpngReq unmarshal(Reader reader) throws JAXBException, FileNotFoundException, XMLStreamException,
            FactoryConfigurationError {

        JAXBContext context = JAXBContext.newInstance(DocumentOpngReq.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        XMLStreamReader streamReader = XMLInputFactory.newInstance().createXMLStreamReader(reader);
        XMLStreamReaderImpl xmlStreamReaderImpl = new XMLStreamReaderImpl(streamReader);

        JAXBElement<DocumentOpngReq> unmarshal = unmarshaller.unmarshal(xmlStreamReaderImpl, DocumentOpngReq.class);
        DocumentOpngReq document = unmarshal.getValue();

        return document;
    }

    @Test
    public void testMarshal() throws IOException, JAXBException, SAXException, XMLStreamException,
            FactoryConfigurationError {
        // prepare
        File file = getTestFile("acmt/acmt.007.001.01.xml");
        Reader reader = new FileReader(file);
        DocumentOpngReq document = unmarshal(reader);
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        // marshal
        marshal(document, os);

        // assert
        assertTrue(os.size() > 0);
        System.out.println(os);
    }

    private void marshal(DocumentOpngReq document, OutputStream os) throws JAXBException, PropertyException,
            XMLStreamException, FactoryConfigurationError, SAXException, IOException {

        JAXBContext context = JAXBContext.newInstance(DocumentOpngReq.class);
        Marshaller marshaller = context.createMarshaller();

        QName qName = new QName("urn:iso:std:iso:20022:tech:xsd:acmt.007.001.01", "document");
        JAXBElement<DocumentOpngReq> element = new JAXBElement<DocumentOpngReq>(qName, DocumentOpngReq.class, null,
                document);
        XMLStreamWriter writer = XMLOutputFactory.newInstance().createXMLStreamWriter(os);
        XMLStreamWriterImpl xmlStreamWriterImpl = new XMLStreamWriterImpl(writer, element.getName());

        marshaller.marshal(element, xmlStreamWriterImpl);
    }

    private File getTestFile(String path) throws IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getFile();
    }
}
