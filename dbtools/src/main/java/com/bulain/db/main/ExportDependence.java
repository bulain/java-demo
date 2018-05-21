package com.bulain.db.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.db.base.Base;
import com.bulain.db.service.Export;

public class ExportDependence extends Base {
    private static Logger logger = LoggerFactory.getLogger(ExportDependence.class);
    private static final String TABLE_NAMES = "users";

    public static void main(String[] args) {
        new ExportDependence().run();
    }

    private void run() {
        try {
            setupBeforeInitDispatcher();
            Export export = (Export) applicationContext.getBean("export");
            export.exportAllDependentTables("data/exportDependence.xml", TABLE_NAMES);
            logger.info("Successful export dependence table!");
        } catch (Exception e) {
            logger.error("run()", e);
        }
    }
}