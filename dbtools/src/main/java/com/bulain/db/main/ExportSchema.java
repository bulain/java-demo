package com.bulain.db.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.db.base.Base;
import com.bulain.db.service.Export;

public class ExportSchema extends Base {
    private static Logger logger = LoggerFactory.getLogger(ExportSchema.class);

    public static void main(String[] args) {
        new ExportSchema().export();
    }

    private void export() {
        try {
            setupBeforeInitDispatcher();
            Export export = (Export) applicationContext.getBean("export");
            export.exportSchema("data/exportSchema.xml");
            logger.info("Successful export schema!");
        } catch (Exception e) {
            logger.error("run()", e);
        }
    }
}
