package com.bulain.hibernate.entity;
// Generated 2011-10-25 20:15:48 by Hibernate Tools 3.2.2.GA



/**
 * Apple generated by hbm2java
 */
public class Apple extends com.bulain.hibernate.entity.Fruit implements java.io.Serializable {


     /**
     * 
     */
    private static final long serialVersionUID = -4923091793763099977L;
    private String appleInfo;

    public Apple() {
    }

    public Apple(String name, String appleInfo) {
        super(name);        
       this.appleInfo = appleInfo;
    }
   
    public String getAppleInfo() {
        return this.appleInfo;
    }
    
    public void setAppleInfo(String appleInfo) {
        this.appleInfo = appleInfo;
    }




}


