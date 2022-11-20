package com.bulain.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class JdbcTemplateDemo {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;
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
    public void testFind() throws IOException {
        String sql = "select * from blog limit 1000000";
        jdbcTemplate.setFetchSize(10000);
        jdbcTemplate.query(sql, new RowSetExtractor());
    }

    static class RowSetExtractor implements ResultSetExtractor<Integer> {
        private Logger logger = LoggerFactory.getLogger(getClass());

        private List<Integer> ints = Arrays.asList(new Integer[]{Types.TINYINT, Types.SMALLINT, Types.INTEGER,
                Types.BIGINT});
        private List<Integer> decimals = Arrays.asList(new Integer[]{Types.FLOAT, Types.DOUBLE, Types.NUMERIC,
                Types.DECIMAL});
        private List<Integer> varchars = Arrays.asList(new Integer[]{Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR,
                Types.NCHAR, Types.NVARCHAR, Types.LONGNVARCHAR});
        private List<Integer> dates = Arrays.asList(new Integer[]{Types.DATE, Types.TIME, Types.TIMESTAMP,
                Types.DECIMAL});

        @Override
        public Integer extractData(ResultSet rowSet) throws SQLException, DataAccessException {

            SXSSFWorkbook workbook = new SXSSFWorkbook();
            workbook.setCompressTempFiles(true);

            //XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("blog");

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

            int rowIdx = 0;
            Row row = null;
            ResultSetMetaData metaData = null;
            String value = null;
            int type, i, columnCount;
            Date d;
            while (rowSet.next()) {
                if (metaData == null) {
                    metaData = rowSet.getMetaData();
                    columnCount = metaData.getColumnCount();

                    row = sheet.createRow(rowIdx++);
                    for (i = 1; i <= columnCount; i++) {
                        String label = metaData.getColumnLabel(i);
                        row.createCell(i - 1).setCellValue(label);
                    }
                }

                row = sheet.createRow(rowIdx++);
                columnCount = metaData.getColumnCount();
                for (i = 1; i <= columnCount; i++) {
                    type = metaData.getColumnType(i);

                    if (ints.contains(type)) {
                        value = String.valueOf(rowSet.getLong(i));
                    } else if (decimals.contains(type)) {
                        value = String.valueOf(rowSet.getBigDecimal(i));
                    } else if (varchars.contains(type)) {
                        value = rowSet.getString(i);
                    } else if (dates.contains(type)) {
                        d = rowSet.getDate(i);
                        if (d != null) {
                            value = df.format(d);
                        }
                    } else {
                        value = rowSet.getString(i);
                    }
                    row.createCell(i - 1).setCellValue(value);
                }

            }

            try {
                /*OutputStream os = new FileOutputStream("target/poidump.xlsx");
                workbook.write(os);
                os.close();
                workbook.dispose();*/

                ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("target/jdbctemplatedump.zip"));
                zipOut.putNextEntry(new ZipEntry("jdbctemplatedump.xlsx"));
                workbook.write(zipOut);
                zipOut.flush();
                zipOut.close();
                workbook.dispose();
            } catch (Exception e) {
                logger.error("extractData()-", e);
            }

            return 0;
        }

    }

}
