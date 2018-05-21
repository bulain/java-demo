package com.bulain.camel.jms;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class MsgBean {
    
    private int counter;
    
    public InputStream generateMessage(){
        String msg = "Test Message: " + counter++;
        InputStream is = new ByteArrayInputStream(msg.getBytes());
        return is;
    }
    
}
