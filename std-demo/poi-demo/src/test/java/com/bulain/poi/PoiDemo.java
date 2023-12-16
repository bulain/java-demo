package com.bulain.poi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class PoiDemo {
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

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);
        
        //XSSFWorkbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("blog");

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String sql = "select * from blog limit 1000000";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);

        int rowIdx = 0;
        Row row = null;
        SqlRowSetMetaData metaData = null;
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

        /*OutputStream os = new FileOutputStream("target/poidump.xlsx");
        workbook.write(os);
        os.close();
        workbook.dispose();*/

        ZipOutputStream zipOut = new ZipOutputStream(new FileOutputStream("target/poidump.zip"));
        zipOut.putNextEntry(new ZipEntry("poidump.xlsx"));
        workbook.write(zipOut);
        zipOut.flush();
        zipOut.close();
        workbook.dispose();

    }

}
