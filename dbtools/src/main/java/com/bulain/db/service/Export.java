package com.bulain.db.service;

public interface Export {
    void exportSchema(String file) throws Exception;
    void exportTables(String file, String[] tableNames) throws Exception;
    void exportAllDependentTables(String file, String tableName) throws Exception;
}