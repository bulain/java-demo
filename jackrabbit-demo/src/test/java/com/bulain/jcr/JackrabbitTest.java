package com.bulain.jcr;

import static junit.framework.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.jcr.Binary;
import javax.jcr.ImportUUIDBehavior;
import javax.jcr.ItemExistsException;
import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.PathNotFoundException;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Value;
import javax.jcr.ValueFactory;
import javax.jcr.Workspace;
import javax.jcr.lock.LockException;
import javax.jcr.nodetype.ConstraintViolationException;
import javax.jcr.nodetype.NodeType;
import javax.jcr.query.Query;
import javax.jcr.query.QueryManager;
import javax.jcr.query.QueryResult;
import javax.jcr.version.Version;
import javax.jcr.version.VersionException;
import javax.jcr.version.VersionManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import com.bulain.common.test.ServiceTestCase;

public class JackrabbitTest extends ServiceTestCase {
    @Autowired
    private Repository jcrRepository;
    private Session session;

    @Before
    public void setUp() throws LoginException, RepositoryException {
        session = jcrRepository.login(new SimpleCredentials("username", "password".toCharArray()));
    }

    @After
    public void tearDown() {
        session.logout();
    }

    @Test
    public void testLogin() {

        String user = session.getUserID();
        String name = jcrRepository.getDescriptor(Repository.REP_NAME_DESC);

        assertEquals("username", user);
        assertEquals("Jackrabbit", name);

        System.out.println("Logged in as " + user + " to a " + name + " repository.");
    }

    @Test
    public void testNode() throws Exception {
        Node root = session.getRootNode();

        // Store content
        Node hello = root.addNode("hello");
        Node world = hello.addNode("world");
        world.setProperty("message", "Hello, World!");
        session.save();
        printNode(root);

        // Retrieve content
        Node node = root.getNode("hello/world");
        System.out.println(node.getPath());
        System.out.println(node.getProperty("message").getString());

        // Remove content
        root.getNode("hello").remove();
        session.save();

        // test
        root.addNode("xxx1");
        Node xxx2 = root.addNode("xxx1/xxx2");
        xxx2.setProperty("xxx2", "I like this!");
        ValueFactory valueFactory = session.getValueFactory();
        ClassPathResource classPathResource = new ClassPathResource("com/bulain/jcr/JackrabbitTest.xml");
        Binary value = valueFactory.createBinary(classPathResource.getInputStream());
        xxx2.setProperty("bin2", value);

        Node node2 = session.getNode("/xxx1/xxx2");
        System.out.println(node2.getPath());
        System.out.println(node2.getProperty("xxx2").getString());
        Property prop = node2.getProperty("bin2");
        Binary binary = prop.getBinary();

        byte[] bs = new byte[1024];
        Arrays.fill(bs, (byte) 0);
        long pos = 0;
        int size = 0;
        while ((size = binary.read(bs, pos)) > 0) {
            System.out.print(new String(bs));
            Arrays.fill(bs, (byte) 0);
            pos += size;
        }

        root.getNode("xxx1").remove();
        session.save();
    }

    @Test
    public void testImport() throws Exception {
        Node root = session.getRootNode();

        // Import the XML file unless already imported
        if (!root.hasNode("importxml")) {
            System.out.print("Importing xml... ");

            // Create an unstructured node under which to import the XML
            Node node = root.addNode("importxml", "nt:unstructured");

            // Import the file "test.xml" under the created node
            ClassPathResource classPathResource = new ClassPathResource("com/bulain/jcr/JackrabbitTest.xml");
            FileInputStream xml = new FileInputStream(classPathResource.getFile());
            session.importXML(node.getPath(), xml, ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
            xml.close();
            session.save();
            System.out.println("done.");
        }

        // output the repository content
        dump(root);
    }

    /** Recursively outputs the contents of the given node. */
    private static void dump(Node node) throws RepositoryException {
        // First output the node path
        System.out.println(node.getPath());
        // Skip the virtual (and large!) jcr:system subtree
        if (node.getName().equals("jcr:system")) {
            return;
        }

        // Then output the properties
        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
            Property property = properties.nextProperty();
            if (property.getDefinition().isMultiple()) {
                // A multi-valued property, print all values
                Value[] values = property.getValues();
                for (int i = 0; i < values.length; i++) {
                    System.out.println(property.getPath() + " = " + values[i].getString());
                }
            } else {
                // A single-valued property
                System.out.println(property.getPath() + " = " + property.getString());
            }
        }

        // Finally output all the child nodes recursively
        NodeIterator nodes = node.getNodes();
        while (nodes.hasNext()) {
            dump(nodes.nextNode());
        }
    }

    @Test
    public void testVersion() throws ItemExistsException, PathNotFoundException, VersionException,
            ConstraintViolationException, LockException, RepositoryException {
        Workspace workspace = session.getWorkspace();
        VersionManager versionManager = workspace.getVersionManager();

        Node root = session.getRootNode();
        Node node = root.addNode("NewNode");
        node.setProperty("maqujun", "handsome");
        node.addMixin("mix:versionable");
        session.save();
        versionManager.checkin(node.getPath());

        node = root.getNode("NewNode");
        System.out.println(node.getProperty("maqujun").getString());
        Version version = versionManager.checkpoint("/NewNode");
        System.out.println(version.getName());
        versionManager.checkout(node.getPath());
        node.setProperty("maqujun", "handsomexx");
        session.save();
        versionManager.checkin(node.getPath());

        node = root.getNode("NewNode");
        System.out.println(node.getProperty("maqujun").getString());
        version = versionManager.checkpoint("/NewNode");
        System.out.println(version.getName());
        node.remove();
        session.save();
    }

    @Test
    public void testFile() throws RepositoryException, IOException {
        Node rootNode = session.getRootNode();

        Node folder = rootNode.addNode("testFolder", NodeType.NT_FOLDER);
        session.save();

        for (int i = 0; i < 10; i++) {
            Node file = folder.addNode("testFile" + i, NodeType.NT_FILE);
            Node resource = file.addNode(Node.JCR_CONTENT, NodeType.NT_RESOURCE);
            resource.setProperty(Property.JCR_MIMETYPE, "text/xml");
            resource.setProperty(Property.JCR_ENCODING, "UTF-8");
            ValueFactory valueFactory = session.getValueFactory();
            ClassPathResource classPathResource = new ClassPathResource("com/bulain/jcr/JackrabbitTest.xml");
            Binary value = valueFactory.createBinary(classPathResource.getInputStream());
            resource.setProperty(Property.JCR_DATA, value);
            session.save();
        }
        printNode(rootNode);

        // folder.remove();
        // session.save();
        // printNode(rootNode);
    }

    @Test
    public void testQuery() throws RepositoryException {
        QueryManager queryManager = session.getWorkspace().getQueryManager();
        String[] supportedQueryLanguages = queryManager.getSupportedQueryLanguages();
        System.out.println(Arrays.asList(supportedQueryLanguages));

        Query query = queryManager.createQuery("SELECT * FROM [nt:resource] where [jcr:mimeType]='text/xml'",
                Query.JCR_SQL2);
        QueryResult queryResult = query.execute();
        NodeIterator nodes = queryResult.getNodes();
        while (nodes.hasNext()) {
            Node nextNode = nodes.nextNode();
            printNode(nextNode);
        }
    }

    private void printNode(Node node) throws RepositoryException {
        if (node.getName().equals("jcr:system")) {
            return;
        }

        System.out.println("=======================");
        System.out.println(node.getPath());
        NodeType primaryNodeType = node.getPrimaryNodeType();
        System.out.println(primaryNodeType.getName());
        PropertyIterator properties = node.getProperties();
        while (properties.hasNext()) {
            Property next = properties.nextProperty();
            System.out.printf("%s=%s\n", next.getName(), next.getValue());
        }
        System.out.println("=======================");
        NodeIterator nodes = node.getNodes();
        while (nodes.hasNext()) {
            Node nextNode = nodes.nextNode();
            printNode(nextNode);
        }
    }
}
