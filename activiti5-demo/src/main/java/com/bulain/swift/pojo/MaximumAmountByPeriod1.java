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
 * Java class for MaximumAmountByPeriod1 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="MaximumAmountByPeriod1">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MaxAmt" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}ActiveCurrencyAndAmount"/>
 *         &lt;element name="NbOfDays" type="{urn:iso:std:iso:20022:tech:xsd:acmt.017.001.01}Max3NumericText"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MaximumAmountByPeriod1", propOrder = {"maxAmt", "nbOfDays"})
public class MaximumAmountByPeriod1 {

    @XmlElement(name = "MaxAmt", required = true)
    protected ActiveCurrencyAndAmount maxAmt;
    @XmlElement(name = "NbOfDays", required = true)
    protected String nbOfDays;

    /**
     * Gets the value of the maxAmt property.
     * 
     * @return possible object is {@link ActiveCurrencyAndAmount }
     * 
     */
    public ActiveCurrencyAndAmount getMaxAmt() {
        return maxAmt;
    }

    /**
     * Sets the value of the maxAmt property.
     * 
     * @param value
     *            allowed object is {@link ActiveCurrencyAndAmount }
     * 
     */
    public void setMaxAmt(ActiveCurrencyAndAmount value) {
        this.maxAmt = value;
    }

    /**
     * Gets the value of the nbOfDays property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getNbOfDays() {
        return nbOfDays;
    }

    /**
     * Sets the value of the nbOfDays property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setNbOfDays(String value) {
        this.nbOfDays = value;
    }

}