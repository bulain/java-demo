package com.bulain.drools;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.drools.Message;

public class HelloTest {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	protected KieServices kieServices;
	protected KieContainer kieContainer;

	@Before
	public void setUp() {
		kieServices = KieServices.Factory.get();
		kieContainer = kieServices.getKieClasspathContainer();
	}
	
	@After
	public void tearDown(){
		kieContainer.dispose();
	}

	@Test
	public void testDrools() {
		KieSession kSession = kieContainer.newKieSession("ksession-rules");
		
		Message message = new Message();
		message.setMessage("Hello World");
		message.setStatus(Message.HELLO);
		
		kSession.insert(message);
		kSession.fireAllRules();
		
		logger.info("Message {status:{}, message:{}}", message.getStatus(), message.getMessage());
		
		kSession.dispose();
	}
	
}
