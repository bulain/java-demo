//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.19 at 12:56:09 ���� CST 
//

package com.bulain.swift.pojo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for PartyAndCertificate1 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="PartyAndCertificate1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Pty" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}PartyIdentification41"/>
 *         &lt;element name="Cert" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}Max10KBinary" minOccurs="0"/>
 *         &lt;element name="SgntrOrdr" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}Max15PlusSignedNumericText" minOccurs="0"/>
 *         &lt;element name="Authstn" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}Authorisation1"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PartyAndCertificate1", propOrder = {"pty", "cert", "sgntrOrdr", "authstn"})
public class PartyAndCertificate1 {

    @XmlElement(name = "Pty", required = true)
    protected PartyIdentification41 pty;
    @XmlElement(name = "Cert")
    protected byte[] cert;
    @XmlElement(name = "SgntrOrdr")
    protected String sgntrOrdr;
    @XmlElement(name = "Authstn", required = true)
    protected Authorisation1 authstn;

    /**
     * Gets the value of the pty property.
     * 
     * @return possible object is {@link PartyIdentification41 }
     * 
     */
    public PartyIdentification41 getPty() {
        return pty;
    }

    /**
     * Sets the value of the pty property.
     * 
     * @param value
     *            allowed object is {@link PartyIdentification41 }
     * 
     */
    public void setPty(PartyIdentification41 value) {
        this.pty = value;
    }

    /**
     * Gets the value of the cert property.
     * 
     * @return possible object is byte[]
     */
    public byte[] getCert() {
        return cert;
    }

    /**
     * Sets the value of the cert property.
     * 
     * @param value
     *            allowed object is byte[]
     */
    public void setCert(byte[] value) {
        this.cert = ((byte[]) value);
    }

    /**
     * Gets the value of the sgntrOrdr property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getSgntrOrdr() {
        return sgntrOrdr;
    }

    /**
     * Sets the value of the sgntrOrdr property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setSgntrOrdr(String value) {
        this.sgntrOrdr = value;
    }

    /**
     * Gets the value of the authstn property.
     * 
     * @return possible object is {@link Authorisation1 }
     * 
     */
    public Authorisation1 getAuthstn() {
        return authstn;
    }

    /**
     * Sets the value of the authstn property.
     * 
     * @param value
     *            allowed object is {@link Authorisation1 }
     * 
     */
    public void setAuthstn(Authorisation1 value) {
        this.authstn = value;
    }

}
