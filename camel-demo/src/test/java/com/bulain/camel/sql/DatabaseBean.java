package com.bulain.camel.sql;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Bean that creates the database table
 */
public class DatabaseBean {

    private static final Logger LOG = LoggerFactory.getLogger(DatabaseBean.class);
    private DataSource dataSource;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        String sql = "create table orders (\n"
              + "  id integer primary key,\n"
              + "  item varchar(10),\n"
              + "  amount varchar(5),\n"
              + "  description varchar(30),\n"
              + "  processed boolean\n"
              + ")";

        LOG.info("Creating table orders ...");

        try {
            jdbc.execute("drop table orders");
        } catch (Throwable e) {
            // ignore
        }

        jdbc.execute(sql);

        LOG.info("... created table orders");
    }

    public void destroy() throws Exception {
        JdbcTemplate jdbc = new JdbcTemplate(dataSource);

        try {
            jdbc.execute("drop table orders");
        } catch (Throwable e) {
            // ignore
        }
    }
    
}