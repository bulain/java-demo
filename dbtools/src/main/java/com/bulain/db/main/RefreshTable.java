package com.bulain.db.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bulain.db.base.Base;
import com.bulain.db.service.Import;

public class RefreshTable extends Base {
    private static Logger logger = LoggerFactory.getLogger(RefreshTable.class);

    public static void main(String[] args) {
        new RefreshTable().run();
    }

    private void run() {
        try {
            setupBeforeInitDispatcher();
            Import in = (Import) applicationContext.getBean("import");
            in.importFlatXml("data/refreshTable.xml", "REFRESH");
            logger.info("Successful refresh table!");
        } catch (Exception e) {
            logger.error("run()", e);
        }
    }
}