//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.19 at 12:56:09 ���� CST 
//

package com.bulain.swift.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for OperationMandate1 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="OperationMandate1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Id" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}Max35Text"/>
 *         &lt;element name="ReqrdSgntrNb" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}Max15PlusSignedNumericText"/>
 *         &lt;element name="SgntrOrdrInd" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}YesNoIndicator"/>
 *         &lt;element name="MndtHldr" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}PartyAndCertificate1" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="BkOpr" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}BankTransactionCodeStructure4" maxOccurs="unbounded"/>
 *         &lt;element name="StartDt" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}ISODate" minOccurs="0"/>
 *         &lt;element name="EndDt" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}ISODate" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationMandate1", propOrder = {"id", "reqrdSgntrNb", "sgntrOrdrInd", "mndtHldr", "bkOpr", "startDt",
        "endDt"})
public class OperationMandate1 {

    @XmlElement(name = "Id", required = true)
    protected String id;
    @XmlElement(name = "ReqrdSgntrNb", required = true)
    protected String reqrdSgntrNb;
    @XmlElement(name = "SgntrOrdrInd")
    protected boolean sgntrOrdrInd;
    @XmlElement(name = "MndtHldr")
    protected List<PartyAndCertificate1> mndtHldr;
    @XmlElement(name = "BkOpr", required = true)
    protected List<BankTransactionCodeStructure4> bkOpr;
    @XmlElement(name = "StartDt")
    protected XMLGregorianCalendar startDt;
    @XmlElement(name = "EndDt")
    protected XMLGregorianCalendar endDt;

    /**
     * Gets the value of the id property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the reqrdSgntrNb property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getReqrdSgntrNb() {
        return reqrdSgntrNb;
    }

    /**
     * Sets the value of the reqrdSgntrNb property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setReqrdSgntrNb(String value) {
        this.reqrdSgntrNb = value;
    }

    /**
     * Gets the value of the sgntrOrdrInd property.
     * 
     */
    public boolean isSgntrOrdrInd() {
        return sgntrOrdrInd;
    }

    /**
     * Sets the value of the sgntrOrdrInd property.
     * 
     */
    public void setSgntrOrdrInd(boolean value) {
        this.sgntrOrdrInd = value;
    }

    /**
     * Gets the value of the mndtHldr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the mndtHldr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getMndtHldr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyAndCertificate1 }
     * 
     * 
     */
    public List<PartyAndCertificate1> getMndtHldr() {
        if (mndtHldr == null) {
            mndtHldr = new ArrayList<PartyAndCertificate1>();
        }
        return this.mndtHldr;
    }

    /**
     * Gets the value of the bkOpr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the bkOpr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getBkOpr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BankTransactionCodeStructure4 }
     * 
     * 
     */
    public List<BankTransactionCodeStructure4> getBkOpr() {
        if (bkOpr == null) {
            bkOpr = new ArrayList<BankTransactionCodeStructure4>();
        }
        return this.bkOpr;
    }

    /**
     * Gets the value of the startDt property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getStartDt() {
        return startDt;
    }

    /**
     * Sets the value of the startDt property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setStartDt(XMLGregorianCalendar value) {
        this.startDt = value;
    }

    /**
     * Gets the value of the endDt property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getEndDt() {
        return endDt;
    }

    /**
     * Sets the value of the endDt property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setEndDt(XMLGregorianCalendar value) {
        this.endDt = value;
    }

}
