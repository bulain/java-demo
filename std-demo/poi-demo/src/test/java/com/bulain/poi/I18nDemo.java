package com.bulain.poi;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.jdbc.support.rowset.SqlRowSetMetaData;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class I18nDemo {

    private List<Integer> ints = Arrays
            .asList(new Integer[]{Types.TINYINT, Types.SMALLINT, Types.INTEGER, Types.BIGINT});
    private List<Integer> decimals = Arrays.asList(new Integer[]{Types.FLOAT, Types.DOUBLE, Types.NUMERIC,
            Types.DECIMAL});
    private List<Integer> varchars = Arrays.asList(new Integer[]{Types.CHAR, Types.VARCHAR, Types.LONGVARCHAR,
            Types.NCHAR, Types.NVARCHAR, Types.LONGNVARCHAR});
    private List<Integer> dates = Arrays.asList(new Integer[]{Types.DATE, Types.TIME, Types.TIMESTAMP, Types.DECIMAL});

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private HashMap<String, String> reportMap;
    @Autowired
    private MessageSource messageSource;

    @Test
    public void testReport() throws IOException {
        report("R001");
    }

    public void report(String key, Object... args) throws IOException {

        String sql = reportMap.get(key);

        SXSSFWorkbook workbook = new SXSSFWorkbook();
        workbook.setCompressTempFiles(true);

        Sheet sheet = workbook.createSheet(key);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, args);

        int rowIdx = 0;
        Row row = null;
        SqlRowSetMetaData metaData = null;
        while (rowSet.next()) {
            if (metaData == null) {
                metaData = rowSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                row = sheet.createRow(rowIdx++);
                for (int i = 1; i <= columnCount; i++) {
                    String column = metaData.getColumnLabel(i);
                    String label = messageSource.getMessage(String.format("%s.%s", key, column), new Object[]{},
                            Locale.getDefault());
                    row.createCell(i - 1).setCellValue(label);
                }
            }

            row = sheet.createRow(rowIdx++);
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
                row.createCell(i - 1).setCellValue(value);
            }

        }

        OutputStream os = new FileOutputStream("target/poidump.xlsx");
        workbook.write(os);
        os.close();
        workbook.dispose();

    }

}
