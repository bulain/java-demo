package com.bulain.jcr.dao;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFactory;

import org.springmodules.jcr.JcrCallback;
import org.springmodules.jcr.JcrTemplate;

import com.bulain.jcr.model.Content;

public class ContentDaoImpl implements ContentDao {
    private JcrTemplate jcrTemplate;

    public Content getById(final String id) {
        return (Content) jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node node = session.getNodeByIdentifier(id);
                Content content = new Content();
                copyNode2Content(node, content);
                return content;
            }
        });
    }

    public Content getByPath(final String folderPath, final String relPath) {
        return (Content) jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node folder = ensureFolder(session, folderPath);
                Node node = folder.getNode(relPath);
                Content content = new Content();
                copyNode2Content(node, content);
                return content;
            }
        });
    }

    public void insertByPath(final String folderPath, final String relPath, final Content content) {
        jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node folder = ensureFolder(session, folderPath);
                Node node = folder.addNode(relPath);
                copyContent2Node(session, content, node);
                session.save();
                content.setId(node.getIdentifier());
                return node;
            }
        });
    }

    public void updateByPath(final String folderPath, final String relPath, final Content content) {
        jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node folder = ensureFolder(session, folderPath);
                Node node = folder.getNode(relPath);
                copyContent2Node(session, content, node);
                session.save();
                return node;
            }
        });
    }

    public void deleteByPath(final String folderPath, final String relPath) {
        jcrTemplate.execute(new JcrCallback() {
            public Object doInJcr(Session session) throws IOException, RepositoryException {
                Node folder = ensureFolder(session, folderPath);
                Node node = folder.getNode(relPath);
                node.remove();
                session.save();
                return null;
            }
        });
    }

    public List<Content> find(String statement) {
        return null;
    }

    public List<Content> find(String statement, String language) {
        return null;
    }

    private Node ensureFolder(Session session, String folderPath) throws RepositoryException {
        Node root = session.getRootNode();
        Node folder = null;
        try {
            folder = root.getNode(folderPath);
        } catch (PathNotFoundException e) {
            folder = createFolder(root, folderPath);
        }
        return folder;
    }
    
    private Node createFolder(Node parent, String folderPath) throws RepositoryException{

        String[] splits = folderPath.split("/");
        Node temp = parent;
        for(String split : splits){
            try{
                temp = temp.getNode(split);
            }catch (PathNotFoundException e) {
                temp = temp.addNode(split);
            }
        }
        
        return temp;
    }

    private void copyNode2Content(Node node, Content content) throws RepositoryException, IOException {
        content.setId(node.getIdentifier());
        content.setFileName(node.getProperty(Content.PROP_FILE_NAME).getString());
        content.setContentType(node.getProperty(Content.PROP_CONTENT_TYPE).getString());
        content.setCatagory(node.getProperty(Content.PROP_CATAGORY).getString());
        content.setLang(node.getProperty(Content.PROP_LANG).getString());
        Binary binary = node.getProperty(Content.PROP_BYTES).getBinary();

        long size = binary.getSize();
        byte[] bytes = new byte[(int) size];
        Arrays.fill(bytes, (byte) 0);
        binary.read(bytes, 0);

        content.setBytes(bytes);
    }

    private void copyContent2Node(Session session, Content content, Node node) throws RepositoryException {
        node.setProperty(Content.PROP_FILE_NAME, content.getFileName());
        node.setProperty(Content.PROP_CONTENT_TYPE, content.getContentType());
        node.setProperty(Content.PROP_CATAGORY, content.getCatagory());
        node.setProperty(Content.PROP_LANG, content.getLang());

        byte[] bytes = content.getBytes();
        InputStream is = new ByteArrayInputStream(bytes);
        ValueFactory valueFactory = session.getValueFactory();
        Binary binary = valueFactory.createBinary(is);

        node.setProperty(Content.PROP_BYTES, binary);
    }

    public void setJcrTemplate(JcrTemplate jcrTemplate) {
        this.jcrTemplate = jcrTemplate;
    }

}
