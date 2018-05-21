package com.bulain.zk.pojo;

import com.bulain.common.model.Person;

public class TabnetView {
    
    private Long clientId;
    private String clientName;
    private String clientDesc;
    
    private Long supplierId;
    private String supplierName;
    private String supplierDesc;
    
    private Person client = new Person();
    private Person supplier = new Person();
    
    public Long getClientId() {
        return clientId;
    }
    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
    public String getClientName() {
        return clientName;
    }
    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
    public Long getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
    public String getSupplierName() {
        return supplierName;
    }
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }
    public String getClientDesc() {
        return clientDesc;
    }
    public void setClientDesc(String clientDesc) {
        this.clientDesc = clientDesc;
    }
    public String getSupplierDesc() {
        return supplierDesc;
    }
    public void setSupplierDesc(String supplierDesc) {
        this.supplierDesc = supplierDesc;
    }
    public Person getClient() {
        return client;
    }
    public void setClient(Person client) {
        this.client = client;
    }
    public Person getSupplier() {
        return supplier;
    }
    public void setSupplier(Person supplier) {
        this.supplier = supplier;
    }
    
}
