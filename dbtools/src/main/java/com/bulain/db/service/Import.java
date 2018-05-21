package com.bulain.db.service;

public interface Import {
    /**
     * import flat xml file using operation
     * 
     * @param file Flat xml file
     * @param operation UPDATE, INSERT, REFRESH, DELETE, DELETE_ALL, TRUNCATE_TABLE, CLEAN_INSERT
     * @throws Exception
     */
    void importFlatXml(String file, String operation) throws Exception;

    /**
     * import xml file using operation
     * 
     * @param file Flat xml file
     * @param operation UPDATE, INSERT, REFRESH, DELETE, DELETE_ALL, TRUNCATE_TABLE, CLEAN_INSERT
     * @throws Exception
     */
    void importXml(String file, String operation) throws Exception;
}