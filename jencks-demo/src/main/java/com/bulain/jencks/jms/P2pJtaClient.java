package com.bulain.jencks.jms;

import com.bulain.jencks.model.Content;
import com.bulain.jencks.model.Person;
import com.bulain.jencks.service.ContentService;
import com.bulain.jencks.service.PersonService;

public class P2pJtaClient {
	private static final String folderPath = "folderPath";

	private P2pClient p2pClient;
	private PersonService personService;
	private ContentService contentService;

	public void sendMessage(final String body) {
		// receive message
		String jmsMessageID = p2pClient.sendMessage(body);

		// insert person into database
		Person person = new Person();
		int idLen = jmsMessageID.length();
		int msgLen = body.length();
		person.setFirstName(jmsMessageID.substring(idLen - 19));
		person.setLastName(body.substring(msgLen - 19));
		personService.insert(person);

		// save content into document repository
		Content content = new Content();
		content.setFileName("fileName");
		content.setContentType("contentType");
		content.setCatagory("catagory");
		content.setLang("lang");
		content.setBytes("bytes".getBytes());
		contentService.insert(folderPath, "jcr", content);
	}

	public void setP2pClient(P2pClient p2pClient) {
		this.p2pClient = p2pClient;
	}

	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

}
