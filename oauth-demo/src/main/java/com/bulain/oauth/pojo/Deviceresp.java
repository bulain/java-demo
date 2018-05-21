package com.bulain.oauth.pojo;

public class Deviceresp {

    private String deviceCode;
    private String userCode;
    private String verificationUrl;
    private String qrcodeUrl;
    private long expiresIn;
    private long interval;
    
    public String getDeviceCode() {
        return deviceCode;
    }
    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }
    public String getUserCode() {
        return userCode;
    }
    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
    public String getVerificationUrl() {
        return verificationUrl;
    }
    public void setVerificationUrl(String verificationUrl) {
        this.verificationUrl = verificationUrl;
    }
    public String getQrcodeUrl() {
        return qrcodeUrl;
    }
    public void setQrcodeUrl(String qrcodeUrl) {
        this.qrcodeUrl = qrcodeUrl;
    }
    public long getExpiresIn() {
        return expiresIn;
    }
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
    public long getInterval() {
        return interval;
    }
    public void setInterval(long interval) {
        this.interval = interval;
    }
    
}
