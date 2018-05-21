package com.bulain.db.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.db.base.Base;
import com.bulain.db.service.Export;

public class ExportTable extends Base {
    private static Logger logger = LoggerFactory.getLogger(ExportTable.class);

    private static final String[] TABLE_NAMES = {"users",};

    public static void main(String[] args) {
        new ExportTable().run();
    }

    private void run() {
        try {
            setupBeforeInitDispatcher();
            Export export = (Export) applicationContext.getBean("export");
            export.exportTables("data/exportTable.xml", TABLE_NAMES);
            logger.info("Successful export table!");
        } catch (Exception e) {
            logger.error("run()", e);
        }

    }
}