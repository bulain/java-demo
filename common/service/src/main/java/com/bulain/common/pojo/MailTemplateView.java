package com.bulain.common.pojo;

import com.bulain.common.model.MailTemplate;

public class MailTemplateView extends MailTemplate {
    private static final long serialVersionUID = -2885735095074300884L;

    private String langName;
    private String createdAtName;
    private String updatedAtName;

    public MailTemplateView() {
    }

    public MailTemplateView(MailTemplate model) {
        setId(model.getId());
        setName(model.getName());
        setLang(model.getLang());
        setSubject(model.getSubject());
        setBody(model.getBody());
        setCreatedBy(model.getCreatedBy());
        setCreatedAt(model.getCreatedAt());
        setUpdatedBy(model.getUpdatedBy());
        setUpdatedAt(model.getUpdatedAt());
        if (model.getBody() != null) {
            setBodyName(new String(model.getBody()));
        }
    }

    public String getLangName() {
        return langName;
    }
    public void setLangName(String langName) {
        this.langName = langName;
    }
    public String getCreatedAtName() {
        return createdAtName;
    }
    public void setCreatedAtName(String createdAtName) {
        this.createdAtName = createdAtName;
    }
    public String getUpdatedAtName() {
        return updatedAtName;
    }
    public void setUpdatedAtName(String updatedAtName) {
        this.updatedAtName = updatedAtName;
    }
}
