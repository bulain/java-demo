package com.bulain.common.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.core.Constants;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.bulain.common.exception.DbException;

public class ImportImpl extends AbstractServiceImpl implements Import {
    private Constants constants = new Constants(DatabaseOperation.class);

    protected void before(Connection jdbcConnection) throws SQLException {
    }
    protected void after(Connection jdbcConnection) throws SQLException {
    }

    public void importFlatXml(String file, String operation) {
        Connection jdbcConnection = null;

        try {
            jdbcConnection = DataSourceUtils.getConnection(dataSource);

            before(jdbcConnection);

            IDatabaseConnection connection = createDatabaseConnection(jdbcConnection);
            IDataSet dataSet = importFlatXmlDataSet(file);
            DatabaseOperation databaseOperation = (DatabaseOperation) constants.asObject(operation);
            databaseOperation.execute(connection, dataSet);

            after(jdbcConnection);
        } catch (DataSetException e) {
            throw new DbException("Import Flat Xml Error", e);
        } catch (IOException e) {
            throw new DbException("Import Flat Xml Error", e);
        } catch (DatabaseUnitException e) {
            throw new DbException("Import Flat Xml Error", e);
        } catch (SQLException e) {
            throw new DbException("Import Flat Xml Error", e);
        } finally {
            DataSourceUtils.releaseConnection(jdbcConnection, dataSource);
        }
    }

    private IDataSet importFlatXmlDataSet(String file) throws DataSetException, IOException {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        InputStream inputStream = new ClassPathResource(file).getInputStream();
        IDataSet dataSet = builder.build(inputStream);
        return dataSet;
    }
}
