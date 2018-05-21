package com.bulain.zk.vm;

import java.io.FileNotFoundException;
import java.io.Serializable;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Filedownload;

@VariableResolver(DelegatingVariableResolver.class)
public class UploadVm implements Serializable {
    private static final long serialVersionUID = 4782272689397636992L;

    @Command
    public void upload(@ContextParam(ContextType.TRIGGER_EVENT) UploadEvent evt) {
        Media media = evt.getMedia();
        String name = media.getName();
        String contentType = media.getContentType();
        String format = media.getFormat();
        System.out.printf("%s, %s, %s\n", name, contentType, format);
    }
    @Command
    public void download() {
        try {
            Filedownload.save("/upload/list.zul", null);
        } catch (FileNotFoundException e) {
            Clients.showNotification("Download error");
        }
    }
}
