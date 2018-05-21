package com.bulain.camel;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.Test;

/**
 * This example is from http://camel.apache.org/
 */
public class Jms2FileDemo {

    @Test
    public void testCamel() throws Exception {

        CamelContext context = new DefaultCamelContext();
        
        // Set up the ActiveMQ JMS Components
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("vm://localhost?broker.persistent=false");
        context.addComponent("test-jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        
        // Add some configuration by hand ...
        context.addRoutes(new RouteBuilder() {
            public void configure() {
                from("test-jms:queue:test.queue").to("file://target/test.queue");
            }
        });
        
        // Camel template - a handy class for kicking off exchanges
        ProducerTemplate template = context.createProducerTemplate();
        
        // Now everything is set up - lets start the context
        context.start();
        
        // Now send some test text to a component - for this case a JMS Queue
        // The text get converted to JMS messages - and sent to the Queue
        // test.queue
        // The file component is listening for messages from the Queue
        // test.queue, consumes
        // them and stores them to disk. The content of each file will be the
        // test we sent here.
        // The listener on the file component gets notified when new files are
        // found ... that's it!
        for (int i = 0; i < 10; i++) {
            template.sendBody("test-jms:queue:test.queue", "Test Message: " + i);
        }

        // wait a bit and then stop
        Thread.sleep(1000);
        context.stop();

    }

}
