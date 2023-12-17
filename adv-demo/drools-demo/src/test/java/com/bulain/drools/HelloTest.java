package com.bulain.drools;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Disabled
class HelloTest {

	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	protected KieServices kieServices;
	protected KieContainer kieContainer;

	@BeforeEach
	public void setUp() {
		kieServices = KieServices.Factory.get();
		kieContainer = kieServices.getKieClasspathContainer();
	}
	
	@Test
	void testDrools() {
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
