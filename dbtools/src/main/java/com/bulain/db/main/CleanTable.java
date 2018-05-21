package com.bulain.db.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.db.base.Base;
import com.bulain.db.service.Import;

public class CleanTable extends Base {
    private static Logger logger = LoggerFactory.getLogger(CleanTable.class);

    public static void main(String[] args) {
        new CleanTable().run();
    }

    private void run() {
        try {
            setupBeforeInitDispatcher();
            Import in = (Import) applicationContext.getBean("import");
            in.importFlatXml("data/cleanTable.xml", "DELETE_ALL");
            logger.info("Successful clean table!");
        } catch (Exception e) {
            logger.error("run()", e);
        }

    }
}
