package com.bulain.cxf.soap;

import java.io.OutputStream;
import java.util.List;

import javax.xml.stream.XMLStreamWriter;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.staxutils.StaxUtils;
public class CdataOutInterceptor extends AbstractPhaseInterceptor<Message> {

    private List<String> cdata;

    public CdataOutInterceptor() {
        super(Phase.WRITE);
    }

    public void handleMessage(Message message) {
        message.put("disable.outputstream.optimization", Boolean.TRUE);
        XMLStreamWriter writer = StaxUtils.createXMLStreamWriter(message.getContent(OutputStream.class));
        message.setContent(XMLStreamWriter.class, new CdataXmlStreamWriter(writer, cdata));
    }

    public void setCdata(List<String> cdata) {
        this.cdata = cdata;
    }

}
