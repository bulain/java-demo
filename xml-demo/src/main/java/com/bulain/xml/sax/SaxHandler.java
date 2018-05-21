package com.bulain.xml.sax;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SaxHandler extends DefaultHandler {
	private static final Logger logger = LoggerFactory
			.getLogger(SaxHandler.class);

	public void startDocument() throws SAXException {
		logger.debug("startDocument() - start");

		logger.debug("startDocument() - end");
	}

	public void endDocument() throws SAXException {
		logger.debug("endDocument() - start");

		logger.debug("endDocument() - end");
	}

	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		logger.debug("startElement(String, String, String, Attributes) - start");

		System.out.printf("Sax: qName=%s\n", qName);
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.printf("Sax: %s=%s\n", attributes.getQName(i),
					attributes.getValue(i));
		}

		logger.debug("startElement(String, String, String, Attributes) - end");
	}

	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		logger.debug("endElement(String, String, String) - start");

		logger.debug("endElement(String, String, String) - end");
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		logger.debug("characters(char[], int, int) - start");

		logger.debug("characters(char[], int, int) - end");
	}

}
