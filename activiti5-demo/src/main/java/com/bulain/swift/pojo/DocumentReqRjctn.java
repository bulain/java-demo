//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.19 at 02:53:55 ���� CST 
//

package com.bulain.swift.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for Document complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="Document">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="AcctReqRjctn" type="{urn:iso:std:iso:20022:tech:xsd:acmt.011.001.01}AccountRequestRejectionV01"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Document", propOrder = {"acctReqRjctn"})
public class DocumentReqRjctn {

    @XmlElement(name = "AcctReqRjctn", required = true)
    protected AccountRequestRejectionV01 acctReqRjctn;

    /**
     * Gets the value of the acctReqRjctn property.
     * 
     * @return possible object is {@link AccountRequestRejectionV01 }
     * 
     */
    public AccountRequestRejectionV01 getAcctReqRjctn() {
        return acctReqRjctn;
    }

    /**
     * Sets the value of the acctReqRjctn property.
     * 
     * @param value
     *            allowed object is {@link AccountRequestRejectionV01 }
     * 
     */
    public void setAcctReqRjctn(AccountRequestRejectionV01 value) {
        this.acctReqRjctn = value;
    }

}