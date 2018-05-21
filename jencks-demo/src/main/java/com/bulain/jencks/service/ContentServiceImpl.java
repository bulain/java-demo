package com.bulain.jencks.service;

import java.util.List;

import com.bulain.jencks.dao.ContentDao;
import com.bulain.jencks.model.Content;

public class ContentServiceImpl implements ContentService {

	private ContentDao contentDao;

	public Content get(String id) {
		return contentDao.getById(id);
	}

	public Content get(String folderPath, String relPath) {
		return contentDao.getByPath(folderPath, relPath);
	}

	public void insert(String folderPath, String relPath, Content content) {
		contentDao.insertByPath(folderPath, relPath, content);
	}

	public void update(String folderPath, String relPath, Content content) {
		contentDao.updateByPath(folderPath, relPath, content);
	}

	public void delete(String folderPath, String relPath) {
		contentDao.deleteByPath(folderPath, relPath);
	}

	public List<Content> find(String statement) {
		return contentDao.find(statement);
	}

	public List<Content> find(String statement, String language) {
		return contentDao.find(statement, language);
	}

	public void setContentDao(ContentDao contentDao) {
		this.contentDao = contentDao;
	}

}
