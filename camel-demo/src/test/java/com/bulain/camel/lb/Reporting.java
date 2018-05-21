package com.bulain.camel.lb;

import org.apache.camel.Body;
import org.apache.camel.Header;

public class Reporting {
    
    public Report updateReport(@Body Report report, @Header("minaServer") String name) {
        report.setReply("Report updated from MINA server running on: " + name);

        // send the report updated
        return report;
    }
}