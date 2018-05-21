package com.bulain.svnkit;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.tmatesoft.svn.core.SVNDepth;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.internal.wc.DefaultSVNOptions;
import org.tmatesoft.svn.core.wc.SVNClientManager;
import org.tmatesoft.svn.core.wc.SVNDiffClient;
import org.tmatesoft.svn.core.wc.SVNRevision;

public class SvnkitDiffTest {
    private String distPath;
    
    private SVNClientManager clientManager;
    private SVNDiffClient diffClient;

    @Before
    public void setUp() {
        distPath = "/javaproject/demo";
        DefaultSVNOptions options = new DefaultSVNOptions();
        String username = "bulain";
        String password = "bulain";
        clientManager = SVNClientManager.newInstance(options, username, password);
        diffClient = clientManager.getDiffClient();
    }

    @After
    public void tearDown() {
        clientManager.dispose();
    }

    @Test
    public void testDiff() throws SVNException, IOException {
        File path = new File(distPath);
        SVNRevision startRevision = SVNRevision.HEAD;
        SVNRevision endRevision = SVNRevision.WORKING;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        diffClient.doDiff(path, SVNRevision.UNDEFINED, startRevision, endRevision, SVNDepth.INFINITY, false,
                outputStream, null);

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        LineNumberReader lineNumberReader = new LineNumberReader(new InputStreamReader(byteArrayInputStream));

        String absolutePath = path.getAbsolutePath();
        absolutePath = absolutePath.replace(File.separator, "/");
        int lastIndexOf = absolutePath.lastIndexOf("/");
        String baseStr = absolutePath.substring(0, lastIndexOf);

        CharArrayWriter writer = new CharArrayWriter();
        String readLine = null;
        while ((readLine = lineNumberReader.readLine()) != null) {
            writer.write(replace(readLine, baseStr) + "\n");
        }

        System.out.println(writer.toString());
    }

    private String replace(String str, String dirStr) {
        String indexStr = "Index: ";
        String addStr = "\\+\\+\\+ ";
        String delStr = "\\-\\-\\- ";

        str = str.replaceAll("^" + indexStr + dirStr, indexStr);
        str = str.replaceAll("^" + addStr + dirStr, addStr);
        str = str.replaceAll("^" + delStr + dirStr, delStr);

        return str;
    }
}
