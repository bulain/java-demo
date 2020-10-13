package com.bulain.common.db;

public interface Import {
    /**
     * import xml file using operation.
     * 
     * @param file
     *            Flat xml file
     * @param operation
     *            UPDATE, INSERT, REFRESH, DELETE, DELETE_ALL, TRUNCATE_TABLE,
     *            CLEAN_INSERT
     * @throws DbException
     */
    void importFlatXml(String file, String operation);
}