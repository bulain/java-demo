package com.bulain.cxf.soap;

import java.util.List;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.staxutils.DelegatingXMLStreamWriter;
  
public class CdataXmlStreamWriter extends DelegatingXMLStreamWriter {  
  
    private List<String> cdata;
    private String currentNode;  
  
    public CdataXmlStreamWriter(XMLStreamWriter writer, List<String> cdata) {  
        super(writer);  
        this.cdata = cdata;
    }  
  
    @Override  
    public void writeCharacters(String text) throws XMLStreamException {  
        boolean useCData = isNeedCData();  
        if (useCData) {  
            super.writeCData(text);  
        } else {  
            super.writeCharacters(text);  
        }  
    }  
  
    private boolean isNeedCData() {  
        return cdata.contains(currentNode);  
    }  
  
    public void writeStartElement(String prefix, String local, String uri)  
            throws XMLStreamException {  
        currentNode = local;  
        super.writeStartElement(prefix, local, uri);  
    }  
    
}  