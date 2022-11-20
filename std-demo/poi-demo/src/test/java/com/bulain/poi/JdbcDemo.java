package com.bulain.poi;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import javax.sql.DataSource;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class JdbcDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private List<Integer> ints = Arrays
            .asList(new Integer[]{Types.TINYINT, Types.SMALLINT, Types.INTEGER, Types.BIGINT});
    private List<Integer> decimals = Arrays.asList(new Integer[]{Types.FLOAT, Types.DOUBLE, Types.NUMERIC,
            Types.DECIMAL});
    private List<Integer> varchars = Arrays.asList(new Integer[]{Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR,
            Types.NCHAR, Types.NVARCHAR, Types.LONGNVARCHAR});
    private List<Integer> dates = Arrays.asList(new Integer[]{Types.DATE, Types.TIME, Types.TIMESTAMP, Types.DECIMAL});

    @Autowired
    private DataSource dataSource;
    private java.util.Date startAt;

    @BeforeEach
    public void setUp() {
        logger.info("{}-start", Thread.currentThread());

        startAt = new java.util.Date();
    }

    @AfterEach
    public void tearDown() {
        java.util.Date endAt = new java.util.Date();
        double during = (endAt.getTime() - startAt.getTime()) / 1000d;

        logger.info("{}-end: {}s", new Object[]{Thread.currentThread(), during});
    }

    @Test
    public void testFind() throws Exception {

        //OutputStream zipOut = new FileOutputStream("target/jdbcdump.csv");

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("target/jdbcdump.zip"));
        zipOut.putNextEntry(new ZipEntry("jdbcdump.csv"));
        PrintStream os = new PrintStream(zipOut);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String sql = "select * from blog limit 1000000";

        Connection conn = dataSource.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setFetchSize(10000);
        ResultSet rowSet = pstmt.executeQuery();

        int rowIdx = 0;
        ResultSetMetaData metaData = null;
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
