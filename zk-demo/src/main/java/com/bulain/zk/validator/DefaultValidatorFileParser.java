package com.bulain.zk.validator;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.bulain.zk.util.DomUtil;
import com.bulain.zk.util.ObjectFactory;
import com.bulain.zk.validator.ValidatorConfig.Builder;

public class DefaultValidatorFileParser implements ValidatorFileParser {

    public Map<String, List<ValidatorConfig>> parseValidatorConfigs(ValidatorFactory validatorFactory, InputStream is,
            String resourceName) {

        Map<String, List<ValidatorConfig>> mapValidatorConfig = new HashMap<String, List<ValidatorConfig>>();

        InputSource in = new InputSource(is);
        in.setSystemId(resourceName);

        Document doc = DomUtil.parse(in);
        if (doc != null) {
            NodeList cmdNodes = doc.getElementsByTagName("command");
            for (int i = 0; i < cmdNodes.getLength(); i++) {
                Element commandElement = (Element) cmdNodes.item(i);

                String cmdName = commandElement.getAttribute("name");
                String namespace = commandElement.getAttribute("namespace");

                List<ValidatorConfig> listValidatorConfig = new ArrayList<ValidatorConfig>();

                NodeList fldNodes = commandElement.getElementsByTagName("field");
                for (int j = 0; j < fldNodes.getLength(); j++) {
                    Element fldElement = (Element) fldNodes.item(j);

                    String fldName = fldElement.getAttribute("name");

                    NodeList fvNodes = fldElement.getElementsByTagName("field-validator");
                    for (int k = 0; k < fvNodes.getLength(); k++) {
                        Element fvElement = (Element) fvNodes.item(k);

                        String fvtype = fvElement.getAttribute("type");

                        Builder builder = new ValidatorConfig.Builder(fvtype);
                        builder.addParam("fieldName", fldName);
                        builder.addParam("namespace", namespace);

                        NodeList paramNodes = fvElement.getElementsByTagName("param");
                        for (int z = 0; z < paramNodes.getLength(); z++) {
                            Element element = (Element) paramNodes.item(z);

                            String paramName = element.getAttribute("name");
                            String paramValue = element.getTextContent();

                            builder.addParam(paramName, paramValue);
                        }

                        NodeList messageNodes = fvElement.getElementsByTagName("message");
                        for (int z = 0; z < messageNodes.getLength(); z++) {
                            Element element = (Element) messageNodes.item(z);

                            String messageKey = element.getAttribute("key");
                            
                            builder.messageKey(messageKey);
                        }

                        listValidatorConfig.add(builder.build());
                    }
                }

                mapValidatorConfig.put(cmdName, listValidatorConfig);
            }
        }

        return mapValidatorConfig;
    }

    public void parseValidatorDefinitions(Map<String, String> validators, InputStream is, String resourceName) {
        InputSource in = new InputSource(is);
        in.setSystemId(resourceName);

        Document doc = DomUtil.parse(in);
        if (doc != null) {
            NodeList nodes = doc.getElementsByTagName("validator");
            for (int i = 0; i < nodes.getLength(); i++) {
                Element validatorElement = (Element) nodes.item(i);
                String name = validatorElement.getAttribute("name");
                String className = validatorElement.getAttribute("class");
                try {
                    ObjectFactory.buildValidator(className, new HashMap<String, String>());
                    validators.put(name, className);
                } catch (Exception e) {
                    throw new RuntimeException("Unable to load validator class " + className, e);
                }
            }
        }
    }

}
