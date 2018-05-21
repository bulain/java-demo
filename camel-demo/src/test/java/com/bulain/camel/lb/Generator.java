package com.bulain.camel.lb;


public class Generator  {

    private static int count;

    public Report createReport() throws Exception {
        int counter = ++count;

        // Create a Report object
        Report report = new Report();
        report.setId(counter);
        report.setTitle("Report Title: " + counter);
        report.setContent("This is a dummy report");

        // Add the report to the Body
        return report;
    }
}