package com.bulain.jbpm4order.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.common.controller.PageSupportActionSupport;
import com.bulain.common.model.Content;
import com.bulain.common.pojo.ContentSearch;
import com.bulain.common.pojo.ContentView;
import com.bulain.common.pojo.Item;
import com.bulain.common.pojo.ItemConst;
import com.bulain.common.service.ContentService;
import com.bulain.common.service.ReferenceService;

public class ContentAction extends PageSupportActionSupport {
    private static final long serialVersionUID = 2135996981420911925L;
    private static final Logger LOG = LoggerFactory.getLogger(ContentAction.class);

    private Long id;

    private ContentSearch search;
    private Content content;
    private List<ContentView> listContent;

    private File blob;
    private String blobFileName;
    private String blobContentType;

    private transient ContentService contentService;
    private transient ReferenceService referenceService;

    private List<Item> listReferenceLang;

    public String list() {
        search = (ContentSearch) getSearchFromSession(ContentSearch.class, search);
        page = getPageFromSession();

        List<Content> list = contentService.page(search, page);
        listContent = formatList(list);

        putSearchToSession(ContentSearch.class, search);
        putPageToSession();

        return SUCCESS;
    }

    public String newn() {
        content = new Content();
        prepareEdit();
        return SUCCESS;
    }
    public String create() {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(blob);
            content.setBytes(bytes);
        } catch (IOException e) {
            LOG.error("create()", e);
        }
        contentService.insert(content);
        return SUCCESS;
    }
    public String show() {
        content = contentService.getWithoutBLOBs(id);
        content = formatItem(content);
        return SUCCESS;
    }
    public String edit() {
        content = contentService.getWithoutBLOBs(id);
        prepareEdit();
        return SUCCESS;
    }
    public String update() {
        if (blob != null) {
            try {
                byte[] bytes = FileUtils.readFileToByteArray(blob);
                content.setBytes(bytes);
            } catch (IOException e) {
                LOG.error("update()", e);
            }
        }
        contentService.update(content, false);
        return SUCCESS;
    }
    public String destroy() {
        contentService.delete(id);
        return SUCCESS;
    }

    public String bulkList() {
        list();
        return SUCCESS;
    }
    public String ajaxList() {
        list();
        return SUCCESS;
    }
    public String ajaxUpload() {
        if (content == null) {
            content = new Content();
        }
        content.setFileName(blobFileName);
        content.setContentType(blobContentType);
        try {
            byte[] bytes = FileUtils.readFileToByteArray(blob);
            content.setBytes(bytes);
        } catch (IOException e) {
            LOG.error("ajaxUpload()", e);
        }
        contentService.insert(content);
        return SUCCESS;
    }
    public String ajaxDestroy() {
        contentService.delete(id);
        return SUCCESS;
    }

    public String download() {
        content = contentService.get(id);
        return SUCCESS;
    }

    public void validate() {
        super.validate();
        if (this.hasErrors()) {
            prepareEdit();
        }
    }

    public void prepareList() {
        listReferenceLang = referenceService.findItem(ItemConst.NAME_LANGUAGE, getLanguage());
    }
    public void prepareNewn() {
        prepareList();
    }
    public void prepareEdit() {
        prepareList();
    }

    protected List<ContentView> formatList(List<Content> list) {
        List<ContentView> listView = new ArrayList<ContentView>();
        for (Content item : list) {
            listView.add(formatItem(item));
        }
        return listView;
    }

    protected ContentView formatItem(Content content) {
        ContentView cv = new ContentView(content);
        cv.setLangName(referenceService.getText(ItemConst.NAME_LANGUAGE, cv.getLang(), getLanguage()));
        return cv;
    }

    public InputStream getInputStream() {
        if (content == null) {
            return null;
        }
        return new ByteArrayInputStream(content.getBytes());
    }

    public String getFileName() {
        if (content == null) {
            return null;
        }
        return content.getFileName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContentSearch getSearch() {
        return search;
    }

    public void setSearch(ContentSearch search) {
        this.search = search;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public List<ContentView> getListContent() {
        return listContent;
    }

    public void setListContent(List<ContentView> listContent) {
        this.listContent = listContent;
    }

    public void setContentService(ContentService contentService) {
        this.contentService = contentService;
    }

    public File getBlob() {
        return blob;
    }

    public void setBlob(File blob) {
        this.blob = blob;
    }

    public String getBlobFileName() {
        return blobFileName;
    }

    public void setBlobFileName(String blobFileName) {
        this.blobFileName = blobFileName;
    }

    public String getBlobContentType() {
        return blobContentType;
    }

    public void setBlobContentType(String blobContentType) {
        this.blobContentType = blobContentType;
    }

    public List<Item> getListReferenceLang() {
        return listReferenceLang;
    }

    public void setListReferenceLang(List<Item> listReferenceLang) {
        this.listReferenceLang = listReferenceLang;
    }

    public void setReferenceService(ReferenceService referenceService) {
        this.referenceService = referenceService;
    }

}
