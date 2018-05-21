package com.bulain.jcr.service;

import java.util.List;

import com.bulain.jcr.model.Content;

public interface ContentService {
    Content get(String id);

    Content get(String folderPath, String relPath);
    void insert(String folderPath, String relPath, Content content);
    void update(String folderPath, String relPath, Content content);
    void delete(String folderPath, String relPath);

    List<Content> find(String statement);
    List<Content> find(String statement, String language);
}
