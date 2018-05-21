package com.bulain.db.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.core.Constants;
import org.springframework.jdbc.datasource.DataSourceUtils;

public class ImportImpl extends AbstractServiceImpl implements Import {
    private Constants constants = new Constants(DatabaseOperation.class);

    protected void before(Connection jdbcConnection) throws SQLException {
    }
    protected void after(Connection jdbcConnection) throws SQLException {
    }

    public void importFlatXml(String file, String operation) throws SQLException, FileNotFoundException,
            DatabaseUnitException {
        Connection jdbcConnection = DataSourceUtils.getConnection(dataSource);
        before(jdbcConnection);

        IDatabaseConnection connection = createDatabaseConnection(jdbcConnection);
        IDataSet dataSet = importFlatXmlDataSet(file);
        DatabaseOperation databaseOperation = (DatabaseOperation) constants.asObject(operation);
        databaseOperation.execute(connection, dataSet);

        after(jdbcConnection);
        DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
    }

    private IDataSet importFlatXmlDataSet(String file) throws DataSetException, FileNotFoundException {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        IDataSet dataSet = builder.build(new FileInputStream(file));
        return dataSet;
    }

    public void importXml(String file, String operation) throws SQLException, FileNotFoundException,
            DatabaseUnitException {
        Connection jdbcConnection = DataSourceUtils.getConnection(dataSource);
        before(jdbcConnection);

        IDatabaseConnection connection = createDatabaseConnection(jdbcConnection);
        IDataSet dataSet = imporXmlDataSet(file);
        DatabaseOperation databaseOperation = (DatabaseOperation) constants.asObject(operation);
        databaseOperation.execute(connection, dataSet);

        after(jdbcConnection);
        DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
    }

    private IDataSet imporXmlDataSet(String file) throws DataSetException, FileNotFoundException {
        IDataSet dataSet = new XmlDataSet(new FileInputStream(file));
        return dataSet;
    }
}
