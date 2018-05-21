package com.bulain.db.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.db.base.Base;
import com.bulain.db.service.Import;

public class ImportTable extends Base {
    private static Logger logger = LoggerFactory.getLogger(ImportTable.class);

    public static void main(String[] args) {
        new ImportTable().run();
    }

    private void run() {
        try {
            setupBeforeInitDispatcher();
            Import in = (Import) applicationContext.getBean("import");
            in.importFlatXml("data/importTable.xml", "CLEAN_INSERT");
            logger.info("Successful import table!");
        } catch (Exception e) {
            logger.error("run()", e);
        }
    }
}