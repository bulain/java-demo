package com.bulain.hibernate.entity;
// Generated 2011-10-25 20:35:54 by Hibernate Tools 3.2.2.GA



/**
 * Desktop generated by hbm2java
 */
public class Desktop extends com.bulain.hibernate.entity.Computer implements java.io.Serializable {


     /**
     * 
     */
    private static final long serialVersionUID = -2160527846799211998L;
    private String desktopInfo;

    public Desktop() {
    }

    public Desktop(String name, String desktopInfo) {
        super(name);        
       this.desktopInfo = desktopInfo;
    }
   
    public String getDesktopInfo() {
        return this.desktopInfo;
    }
    
    public void setDesktopInfo(String desktopInfo) {
        this.desktopInfo = desktopInfo;
    }




}


