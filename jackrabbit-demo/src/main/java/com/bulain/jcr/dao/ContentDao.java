package com.bulain.jcr.dao;

import java.util.List;

import com.bulain.jcr.model.Content;

public interface ContentDao {
    Content getById(String id);

    Content getByPath(String folderPath, String relPath);
    void insertByPath(String folderPath, String relPath, Content content);
    void updateByPath(String folderPath, String relPath, Content content);
    void deleteByPath(String folderPath, String relPath);
    
    List<Content> find(String statement);
    List<Content> find(String statement, String language);
}
