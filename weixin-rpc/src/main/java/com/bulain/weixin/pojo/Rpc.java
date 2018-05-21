package com.bulain.weixin.pojo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "xml")
public class Rpc {
    private String toUserName; //开发者微信号
    private String fromUserName; //发送方帐号（一个OpenID）
    private long createTime; //消息创建时间 （整型）
    private String msgType; //消息类型，text, image, voice, video, shortvideo, location, link, event
    private String msgId; //消息id，64位整型

    //文本消息
    private String content; //文本消息内容

    //图片消息
    private String picUrl; //图片链接

    //小视频消息,视频消息, 语音消息
    private String mediaId; //图片消息媒体id，可以调用多媒体文件下载接口拉取数据。
    private String thumbMediaId; //视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
    private String format; //语音格式，如amr，speex等

    //地理位置消息
    private double location_X; //地理位置纬度
    private double location_Y; //地理位置经度
    private double scale; //地图缩放大小
    private String label; //地理位置信息

    //链接消息
    private String title; // 消息标题
    private String description; //消息描述
    private String url; //消息链接

    //关注/取消关注事件, 扫描带参数二维码事件, 自定义菜单事件
    private String event; //事件类型，subscribe、unsubscribe，SCAN, LOCATION, CLICK, VIEW
    private String eventKey; //事件KEY值，设置的跳转URL

    private String ticket; //二维码的ticket，可用来换取二维码图片

    //上报地理位置事件
    private double latitude; //地理位置纬度
    private double longitude; //地理位置经度
    private double precision; //地理位置精度

    @XmlElement(name = "ToUserName")
    public String getToUserName() {
        return toUserName;
    }
    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    @XmlElement(name = "FromUserName")
    public String getFromUserName() {
        return fromUserName;
    }
    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    @XmlElement(name = "CreateTime")
    public long getCreateTime() {
        return createTime;
    }
    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @XmlElement(name = "MsgType")
    public String getMsgType() {
        return msgType;
    }
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    @XmlElement(name = "Content")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @XmlElement(name = "MsgId")
    public String getMsgId() {
        return msgId;
    }
    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }
    
    @XmlElement(name = "PicUrl")
    public String getPicUrl() {
        return picUrl;
    }
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
    
    @XmlElement(name = "MediaId")
    public String getMediaId() {
        return mediaId;
    }
    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
    
    @XmlElement(name = "ThumbMediaId")
    public String getThumbMediaId() {
        return thumbMediaId;
    }
    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
    
    @XmlElement(name = "Format")
    public String getFormat() {
        return format;
    }
    public void setFormat(String format) {
        this.format = format;
    }
    
    @XmlElement(name = "Location_X")
    public double getLocation_X() {
        return location_X;
    }
    public void setLocation_X(double location_X) {
        this.location_X = location_X;
    }
    
    @XmlElement(name = "Location_Y")
    public double getLocation_Y() {
        return location_Y;
    }
    public void setLocation_Y(double location_Y) {
        this.location_Y = location_Y;
    }
    
    @XmlElement(name = "Scale")
    public double getScale() {
        return scale;
    }
    public void setScale(double scale) {
        this.scale = scale;
    }
    
    @XmlElement(name = "Label")
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    
    @XmlElement(name = "Title")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    
    @XmlElement(name = "Description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    
    @XmlElement(name = "Url")
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    
    @XmlElement(name = "Event")
    public String getEvent() {
        return event;
    }
    public void setEvent(String event) {
        this.event = event;
    }
    
    @XmlElement(name = "EventKey")
    public String getEventKey() {
        return eventKey;
    }
    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }
    
    @XmlElement(name = "Ticket")
    public String getTicket() {
        return ticket;
    }
    public void setTicket(String ticket) {
        this.ticket = ticket;
    }
    
    @XmlElement(name = "Latitude")
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    
    @XmlElement(name = "Longitude")
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    
    @XmlElement(name = "Precision")
    public double getPrecision() {
        return precision;
    }
    public void setPrecision(double precision) {
        this.precision = precision;
    }
    
}
