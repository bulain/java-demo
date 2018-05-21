package com.bulain.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XmlValidateTest {
    @Test
    public void testStreamValidate() throws SAXException, IOException {
        // 1. Lookup a factory for the W3C XML Schema language
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");

        // 2. Compile the schema.
        File testSchema = getTestSchema();
        Schema schema = factory.newSchema(testSchema);

        // 3. Get a validator from the schema.
        Validator validator = schema.newValidator();

        // 4. Parse the document you want to check.
        File testFile = getTestFile();
        Source source = new StreamSource(testFile);

        // 5. Check the document
        validator.validate(source);
    }

    @Test
    public void testSaxValidate() throws IOException, SAXException {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File testSchema = getTestSchema();
        Schema schema = factory.newSchema(testSchema);
        Validator validator = schema.newValidator();

        File testFile = getTestFile();
        Source source = new SAXSource(new InputSource(new FileInputStream(testFile)));

        validator.validate(source);
    }

    @Test
    public void testDomValidate() throws IOException, SAXException, ParserConfigurationException {
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File testSchema = getTestSchema();
        Schema schema = factory.newSchema(testSchema);
        Validator validator = schema.newValidator();
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        File file = getTestFile();
        Document doc = db.parse(file);
        Source source = new DOMSource(doc);
        
        validator.validate(source);
    }
    
    @Test
    public void testStaxEventValidate() throws IOException, XMLStreamException, SAXException{
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File testSchema = getTestSchema();
        Schema schema = factory.newSchema(testSchema);
        Validator validator = schema.newValidator();
        
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        xmlif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);

        File file = getTestFile();
        FileInputStream fis = new FileInputStream(file);
        XMLEventReader eventReader = xmlif.createXMLEventReader(fis);
        Source source = new StAXSource(eventReader);
        
        validator.validate(source);
    }
    
    @Test
    public void testStaxStreamValidate() throws IOException, XMLStreamException, SAXException{
        SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        File testSchema = getTestSchema();
        Schema schema = factory.newSchema(testSchema);
        Validator validator = schema.newValidator();
        
        XMLInputFactory xmlif = XMLInputFactory.newInstance();
        xmlif.setProperty(XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES, Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES, Boolean.FALSE);
        xmlif.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, Boolean.TRUE);
        xmlif.setProperty(XMLInputFactory.IS_COALESCING, Boolean.TRUE);

        File file = getTestFile();
        FileInputStream fis = new FileInputStream(file);
        XMLStreamReader streamReader = xmlif.createXMLStreamReader(fis);
        Source source = new StAXSource(streamReader);
        
        validator.validate(source);
    }

    private File getTestSchema() throws IOException {
        ClassPathResource resource = new ClassPathResource("xsd/student.xsd");
        return resource.getFile();
    }

    private File getTestFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/bulain/xml/student.xml");
        return resource.getFile();
    }
}
