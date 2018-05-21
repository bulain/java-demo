package com.bulain.svnkit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.ISVNLogEntryHandler;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLogEntry;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;

public class SvnkitLogTest {
    private String distPath;
    private String svnURL;
    private SVNClientManager clientManager;
    private SVNLogClient logClient;
    private SVNUpdateClient updateClient;
    private SVNWCClient wcClient;

    @Before
    public void setUp() {
        svnURL = "http://localhost/svn/demo";
        distPath = "/javaproject/demo";
        DefaultSVNOptions options = new DefaultSVNOptions();
        String username = "bulain";
        String password = "bulain";
        clientManager = SVNClientManager.newInstance(options, username, password);
        logClient = clientManager.getLogClient();
        updateClient = clientManager.getUpdateClient();
        wcClient = clientManager.getWCClient();
    }

    @After
    public void tearDown() {
        clientManager.dispose();
    }

    @Test
    public void testCheckout() throws SVNException {
        SVNURL url = SVNURL.parseURIDecoded(svnURL);
        File dstPath = new File(distPath);
        long checkout = updateClient.doCheckout(url, dstPath, SVNRevision.UNDEFINED, SVNRevision.HEAD,
                SVNDepth.INFINITY, false);
        System.out.println("SVN checkout:" + checkout);
    }

    @Test
    public void testProcessSingleFile() throws SVNException, IOException {
        File file = new File(distPath + "/log/test.sql");
        diffFile(file);
    }

    @Test
    public void testProcessFile() throws SVNException, IOException {
        File path = new File(distPath);
        processFile(path);
    }

    private void processFile(File path) throws SVNException, IOException {
        if (path.isDirectory()) {
            File[] listFiles = path.listFiles(new FileFilter() {
                public boolean accept(File file) {
                    return file.isDirectory() || file.getName().toLowerCase().endsWith(".sql");
                }
            });
            for (File file : listFiles) {
                processFile(file);
            }
        } else if (path.isFile()) {
            diffFile(path);
        }
    }

    private void diffFile(File file) throws SVNException, IOException{
        List<SVNLogEntry> listLogEntry = getLogEntrys(file);
        outputDiff(file, listLogEntry);
    }
    
    private List<SVNLogEntry> getLogEntrys(File file) throws SVNException {
        //prepare logClient parameter
        File[] paths = new File[]{file};
        SVNRevision startRevision = SVNRevision.UNDEFINED;
        SVNRevision endRevision = SVNRevision.UNDEFINED;
        boolean stopOnCopy = true;
        boolean discoverChangedPaths = true;
        long limit = -1;
        List<SVNLogEntry> listLogEntry = new ArrayList<SVNLogEntry>();
        ListLogEntryHandler handler = new ListLogEntryHandler(listLogEntry);

        //call logClient and get log entry list
        logClient.doLog(paths, startRevision, endRevision, stopOnCopy, discoverChangedPaths, limit, handler);

        //sort log entry list
        Collections.sort(listLogEntry, new Comparator<SVNLogEntry>() {
            public int compare(SVNLogEntry o1, SVNLogEntry o2) {
                return (int) (o1.getRevision() - o2.getRevision());
            }
        });

        return listLogEntry;
    }

    private void outputDiff(File file, List<SVNLogEntry> listLogEntry) throws SVNException, IOException {
        int prevMaxLineNumber = 0;
        for (SVNLogEntry logEntry : listLogEntry) {
            System.out.println("####################################### "+file.getAbsolutePath());
            System.out.printf("r%d | %s | %s | %d | %s\n", logEntry.getRevision(), logEntry.getAuthor(),
                    logEntry.getDate(), logEntry.getChangedPaths().size(), logEntry.getMessage());

            SVNRevision revision = SVNRevision.create(logEntry.getRevision());

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            wcClient.doGetFileContents(file, SVNRevision.UNDEFINED, revision, false, outputStream);

            int curMaxLineNumber = prevMaxLineNumber;

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(byteArrayInputStream));
            String readLine = null;
            CharArrayWriter writer = new CharArrayWriter();

            while ((readLine = lineNumberReader.readLine()) != null) {
                int lineNumber = lineNumberReader.getLineNumber();

                if (!StringUtils.isBlank(readLine) && lineNumber > curMaxLineNumber) {
                    curMaxLineNumber = lineNumber;
                }

                if (lineNumber > prevMaxLineNumber) {
                    writer.write(readLine + "\n");
                }
            }
            System.out.println(writer.toString());

            prevMaxLineNumber = curMaxLineNumber;
        }
    }
}

/**
 * SVNLogEntry handler
 */
class ListLogEntryHandler implements ISVNLogEntryHandler {
    private List<SVNLogEntry> listLogEntry;

    public ListLogEntryHandler(List<SVNLogEntry> listLogEntry) {
        this.listLogEntry = listLogEntry;
    }

    public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
        listLogEntry.add(logEntry);
    }

    public List<SVNLogEntry> getListLogEntry() {
        return listLogEntry;
    }
}
