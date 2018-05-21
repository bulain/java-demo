package com.bulain.svnkit;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;

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
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNInfo;
import org.tmatesoft.svn.core.wc.SVNLogClient;
import org.tmatesoft.svn.core.wc.SVNRevision;
import org.tmatesoft.svn.core.wc.SVNUpdateClient;
import org.tmatesoft.svn.core.wc.SVNWCClient;

public class SvnkitTest {
    private String distPath;
    private String svnURL;
    
    private SVNClientManager clientManager;
    private SVNDiffClient diffClient;
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
        diffClient = clientManager.getDiffClient();
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
        System.out.println(checkout);
    }

    @Test
    public void testDiff() throws SVNException {
        File path = new File(distPath);
        SVNRevision startRevision = SVNRevision.HEAD;
        SVNRevision endRevision = SVNRevision.WORKING;
        OutputStream outputStream = new ByteArrayOutputStream();

        diffClient.doDiff(path, SVNRevision.UNDEFINED, startRevision, endRevision, SVNDepth.INFINITY, false,
                outputStream, null);

        System.out.println(outputStream.toString());
    }

    @Test
    public void testInfo() throws SVNException {
        File path = new File(distPath);
        SVNInfo info = wcClient.doInfo(path, SVNRevision.WORKING);
        System.out.printf("Path: %s\n", info.getPath());
        System.out.printf("URL: %s\n", info.getURL());
        System.out.printf("Repository Root: %s\n", info.getRepositoryRootURL());
        System.out.printf("Repository UUI %s\n", info.getRepositoryUUID());
        System.out.printf("Revision: %s\n", info.getRevision());
        System.out.printf("Node Kin %s\n", info.getKind());
        System.out.printf("Schedule: %s\n", info.getSchedule());
        System.out.printf("Last Changed Author: %s\n", info.getAuthor());
        System.out.printf("Last Changed Rev: %s\n", info.getCommittedRevision());
        System.out.printf("Last Changed Date: %s\n", info.getCommittedDate());
    }

    @Test
    public void testUpdate() throws SVNException {
        File path = new File(distPath);
        long update = updateClient.doUpdate(path, SVNRevision.HEAD, SVNDepth.INFINITY, true, true);
        System.out.println(update);
    }

    @Test
    public void testLog() throws SVNException {
        File[] paths = new File[]{new File(distPath)};
        SVNRevision startRevision = SVNRevision.UNDEFINED;
        SVNRevision endRevision = SVNRevision.UNDEFINED;
        boolean stopOnCopy = true;
        boolean discoverChangedPaths = true;
        long limit = 100;
        ISVNLogEntryHandler handler = new DebugLogEntryHandler();

        logClient.doLog(paths, startRevision, endRevision, stopOnCopy, discoverChangedPaths, limit, handler);
    }
}

class DebugLogEntryHandler implements ISVNLogEntryHandler {

    public void handleLogEntry(SVNLogEntry logEntry) throws SVNException {
        System.out.printf("r%d | %s | %s | %d \n%s", logEntry.getRevision(), logEntry.getAuthor(), logEntry.getDate(),
                logEntry.getChangedPaths().size(), logEntry.getMessage());
    }

}