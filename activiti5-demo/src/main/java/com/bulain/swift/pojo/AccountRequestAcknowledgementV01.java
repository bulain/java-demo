//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.11.19 at 12:55:44 ���� CST 
//

package com.bulain.swift.pojo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for AccountRequestAcknowledgementV01 complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="AccountRequestAcknowledgementV01">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Refs" type="{urn:iso:std:iso:20022:tech:xsd:acmt.010.001.01}References5"/>
 *         &lt;element name="AcctId" type="{urn:iso:std:iso:20022:tech:xsd:acmt.010.001.01}AccountForAction1" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="OrgId" type="{urn:iso:std:iso:20022:tech:xsd:acmt.010.001.01}OrganisationIdentification6" maxOccurs="unbounded"/>
 *         &lt;element name="AcctSvcrId" type="{urn:iso:std:iso:20022:tech:xsd:acmt.010.001.01}BranchAndFinancialInstitutionIdentification4"/>
 *         &lt;element name="DgtlSgntr" type="{urn:iso:std:iso:20022:tech:xsd:acmt.010.001.01}PartyAndSignature1" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountRequestAcknowledgementV01", propOrder = {"refs", "acctId", "orgId", "acctSvcrId", "dgtlSgntr"})
public class AccountRequestAcknowledgementV01 {

    @XmlElement(name = "Refs", required = true)
    protected References5 refs;
    @XmlElement(name = "AcctId")
    protected List<AccountForAction1> acctId;
    @XmlElement(name = "OrgId", required = true)
    protected List<OrganisationIdentification6> orgId;
    @XmlElement(name = "AcctSvcrId", required = true)
    protected BranchAndFinancialInstitutionIdentification4 acctSvcrId;
    @XmlElement(name = "DgtlSgntr")
    protected List<PartyAndSignature1> dgtlSgntr;

    /**
     * Gets the value of the refs property.
     * 
     * @return possible object is {@link References5 }
     * 
     */
    public References5 getRefs() {
        return refs;
    }

    /**
     * Sets the value of the refs property.
     * 
     * @param value
     *            allowed object is {@link References5 }
     * 
     */
    public void setRefs(References5 value) {
        this.refs = value;
    }

    /**
     * Gets the value of the acctId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the acctId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getAcctId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountForAction1 }
     * 
     * 
     */
    public List<AccountForAction1> getAcctId() {
        if (acctId == null) {
            acctId = new ArrayList<AccountForAction1>();
        }
        return this.acctId;
    }

    /**
     * Gets the value of the orgId property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the orgId property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getOrgId().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrganisationIdentification6 }
     * 
     * 
     */
    public List<OrganisationIdentification6> getOrgId() {
        if (orgId == null) {
            orgId = new ArrayList<OrganisationIdentification6>();
        }
        return this.orgId;
    }

    /**
     * Gets the value of the acctSvcrId property.
     * 
     * @return possible object is
     *         {@link BranchAndFinancialInstitutionIdentification4 }
     * 
     */
    public BranchAndFinancialInstitutionIdentification4 getAcctSvcrId() {
        return acctSvcrId;
    }

    /**
     * Sets the value of the acctSvcrId property.
     * 
     * @param value
     *            allowed object is
     *            {@link BranchAndFinancialInstitutionIdentification4 }
     * 
     */
    public void setAcctSvcrId(BranchAndFinancialInstitutionIdentification4 value) {
        this.acctSvcrId = value;
    }

    /**
     * Gets the value of the dgtlSgntr property.
     * 
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the dgtlSgntr property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * 
     * <pre>
     * getDgtlSgntr().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PartyAndSignature1 }
     * 
     * 
     */
    public List<PartyAndSignature1> getDgtlSgntr() {
        if (dgtlSgntr == null) {
            dgtlSgntr = new ArrayList<PartyAndSignature1>();
        }
        return this.dgtlSgntr;
    }

}
