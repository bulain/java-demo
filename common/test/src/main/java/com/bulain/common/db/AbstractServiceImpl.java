package com.bulain.common.db;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;

public abstract class AbstractServiceImpl {
    protected DataSource dataSource;
    private String schema;
    private Properties databaseConfig;

    protected void updateDatabaseConfig(DatabaseConfig config) throws DatabaseUnitException {
        if (databaseConfig != null) {
            config.setPropertiesByString(databaseConfig);
        }
    }

    protected IDatabaseConnection createDatabaseConnection(Connection jdbcConnection) throws DatabaseUnitException {
        IDatabaseConnection connection = new DatabaseConnection(jdbcConnection, schema);
        updateDatabaseConfig(connection.getConfig());
        return connection;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setDatabaseConfig(Properties databaseConfig) {
        this.databaseConfig = databaseConfig;
    }
}
