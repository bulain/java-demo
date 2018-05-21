package com.bulain.jencks.service;

import java.util.List;

import com.bulain.jencks.model.Content;

public interface ContentService {
	Content get(String id);

	Content get(String folderPath, String relPath);

	void insert(String folderPath, String relPath, Content content);

	void update(String folderPath, String relPath, Content content);

	void delete(String folderPath, String relPath);

	List<Content> find(String statement);

	List<Content> find(String statement, String language);
}
