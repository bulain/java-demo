package com.bulain.ews;

import java.net.URI;

import org.junit.Test;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.MessageBody;

public class EwsDemo {

	@Test
	public void testMail() throws Exception {
		ExchangeService service = new ExchangeService();
		ExchangeCredentials credentials = new WebCredentials("notice@mail.com", "AAaa!!");
		service.setCredentials(credentials);
		service.setUrl(new URI("https://mail.server.com/EWS/Exchange.asmx"));

		EmailMessage msg = new EmailMessage(service);
		msg.setSubject("Hello world!");
		msg.setBody(MessageBody.getMessageBodyFromText("Sent using the EWS Managed API."));
		msg.getToRecipients().add("notice@mail.com");
		msg.getCcRecipients().add("notice@mail.com");
		msg.getBccRecipients().add("notice@mail.com");
		//msg.getAttachments().addFileAttachment("file-path");
		msg.send();
	}

}
