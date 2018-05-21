package com.bulain.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class CsvDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<Integer> ints = Arrays
            .asList(new Integer[]{Types.TINYINT, Types.SMALLINT, Types.INTEGER, Types.BIGINT});
    private List<Integer> decimals = Arrays.asList(new Integer[]{Types.FLOAT, Types.DOUBLE, Types.NUMERIC,
            Types.DECIMAL});
    private List<Integer> varchars = Arrays.asList(new Integer[]{Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR,
            Types.NCHAR, Types.NVARCHAR, Types.LONGNVARCHAR});
    private List<Integer> dates = Arrays.asList(new Integer[]{Types.DATE, Types.TIME, Types.TIMESTAMP, Types.DECIMAL});

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private java.util.Date startAt;

    @Before
    public void setUp() {
        logger.info("{}-start", Thread.currentThread());

        startAt = new java.util.Date();
    }

    @After
    public void tearDown() {
        java.util.Date endAt = new java.util.Date();
        double during = (endAt.getTime() - startAt.getTime()) / 1000d;

        logger.info("{}-end: {}s", new Object[]{Thread.currentThread(), during});
    }

    @Test
    public void testFind() throws IOException {

        //OutputStream zipOut = new FileOutputStream("target/csvdump.csv");

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("target/csvdump.zip"));
        zipOut.putNextEntry(new ZipEntry("csvdump.csv"));
        PrintStream os = new PrintStream(zipOut);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String sql = "select * from blog limit 100000";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        int rowIdx = 0;
        SqlRowSetMetaData metaData = null;
        while (rowSet.next()) {
            if (metaData == null) {
                metaData = rowSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String label = metaData.getColumnLabel(i);
                    if (i < columnCount) {
                        os.printf("%s,", label);
                    } else {
                        os.println(label);
                    }
                }
            }

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                int type = metaData.getColumnType(i);

                String value = null;
                if (ints.contains(type)) {
                    long l = rowSet.getLong(i);
                    value = String.valueOf(l);
                } else if (decimals.contains(type)) {
                    BigDecimal bg = rowSet.getBigDecimal(i);
                    value = String.valueOf(bg);
                } else if (varchars.contains(type)) {
                    value = rowSet.getString(i);
                } else if (dates.contains(type)) {
                    Date d = rowSet.getDate(i);
                    if (d != null) {
                        value = df.format(d);
                    }
                } else {
                    value = rowSet.getString(i);
                }

                if (i < columnCount) {
                    os.printf("%s,", value != null ? value : "");
                } else {
                    os.println(value != null ? value : "");
                }
            }

            if (rowIdx % 1000 == 0) {
                os.flush();
            }
            rowIdx++;
        }

        os.flush();
        os.close();

    }
}
