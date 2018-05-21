package com.bulain.db.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseSequenceFilter;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.database.search.TablesDependencyHelper;
import org.dbunit.dataset.FilteredDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITableMetaData;
import org.dbunit.dataset.filter.ITableFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ExportImpl extends AbstractServiceImpl implements Export {

    private static final String ID = "ID";

    public void exportSchema(String file) throws DatabaseUnitException, SQLException, IOException {
        Connection jdbcConnection = DataSourceUtils.getConnection(dataSource);

        //database connection
        IDatabaseConnection connection = createDatabaseConnection(jdbcConnection);

        // full database export
        // automatically order tables by their foreign keys
        ITableFilter filter = new DatabaseSequenceFilter(connection);
        IDataSet fullDataSet = new FilteredDataSet(filter, connection.createDataSet());

        // write into xml file
        FlatXmlDataSet.write(fullDataSet, new FileOutputStream(file));

        DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
    }

    public void exportTables(String file, String[] tableNames) throws DatabaseUnitException, SQLException, IOException {
        Connection jdbcConnection = DataSourceUtils.getConnection(dataSource);

        //database connection
        IDatabaseConnection connection = createDatabaseConnection(jdbcConnection);

        QueryDataSet metaDataSet = new QueryDataSet(connection);
        for (String name : tableNames) {
            metaDataSet.addTable(name);
        }

        //QueryDataSet
        QueryDataSet partialDataSet = new QueryDataSet(connection);
        for (String name : tableNames) {
            ITableMetaData tableMetaData = metaDataSet.getTableMetaData(name);
            int columnIndex = -1;
            try {
                columnIndex = tableMetaData.getColumnIndex(ID);
            } catch (Exception e) {
            }

            //confirm ID must exists
            if (columnIndex < 0) {
                partialDataSet.addTable(name);
            } else {
                partialDataSet.addTable(name, prepareSql(name, ID));
            }
        }

        //write into xml file
        FlatXmlDataSet.write(partialDataSet, new FileOutputStream(file));

        DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
    }

    public void exportAllDependentTables(String file, String tableName) throws DatabaseUnitException, SQLException,
            IOException {
        Connection jdbcConnection = DataSourceUtils.getConnection(dataSource);

        //database connection
        IDatabaseConnection connection = createDatabaseConnection(jdbcConnection);

        //get all dependent tables
        String[] dependentTableNames = TablesDependencyHelper
                .getAllDependentTables(connection, tableName.toUpperCase());

        //export tables
        exportTables(file, dependentTableNames);

        DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
    }

    private String prepareSql(String tableName, String orderBy) {
        return String.format("select * from %s order by %s", tableName, orderBy);
    }
}
