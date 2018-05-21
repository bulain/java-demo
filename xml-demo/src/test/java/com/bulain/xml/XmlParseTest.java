package com.bulain.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.bulain.xml.sax.SaxHandler;

public class XmlParseTest {

    @Test
    public void testSax() throws Exception  {
        //get test file
        File file = getTestFile();

        //SAX factory
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        
        //parse
        SaxHandler handler = new SaxHandler();
        parser.parse(file, handler);
    }

    @Test
    public void testDom() throws Exception {
        //get test file
        File file = getTestFile();

        //DOM factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        //parse
        Document doc = db.parse(file);
        
        //print
        printNodes(doc);
    }
    
    private void printNodes(Node node){
        if(Node.ELEMENT_NODE==node.getNodeType()){
            System.out.printf("Dom: qName=%s\n",node.getNodeName());
        }
        
        NodeList childNodes = node.getChildNodes();
        int length = childNodes.getLength();
        
        for(int i=0; i<length; i++){
            Node item = childNodes.item(i);
            printNodes(item);
        }
    }

    @Test
    public void testStaxStream() throws XMLStreamException, IOException {
        //get test file
        File file = getTestFile();
        InputStream is = new FileInputStream(file);

        //STAX factory
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLStreamReader xmlStreamReader = inputFactory.createXMLStreamReader(is);

        //loop each stream
        while (xmlStreamReader.hasNext()) {
            int event = xmlStreamReader.next();

            if (event == XMLStreamConstants.START_ELEMENT) {
                System.out.printf("StaxStream: qName=%s\n", xmlStreamReader.getName());
            }
        }
        
        //close resource
        xmlStreamReader.close();
        is.close();
    }

    @Test
    public void testStaxEvent() throws Exception {
        //get test file
        File file = getTestFile();
        InputStream is = new FileInputStream(file);

        //STAX factory
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        XMLEventReader xmlEventReader = inputFactory.createXMLEventReader(is);

        //loop each event
        while (xmlEventReader.hasNext()) {
            XMLEvent event = xmlEventReader.nextEvent();

            if (event.isStartElement()) {
                StartElement start = event.asStartElement();

                System.out.printf("StaxEvent: qName=%s\n", start.getName());
            }
        }
        
        //close resource
        xmlEventReader.close();
        is.close();
    }
    
    private File getTestFile() throws IOException{
        ClassPathResource resource = new ClassPathResource("com/bulain/xml/student.xml");
        return resource.getFile();
    }
}
