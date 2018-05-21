package com.bulain.activiti.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bulain.activiti.model.Joda;
import com.bulain.activiti.pojo.JodaSearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.util.SystemClock;

@DataSet(file = "test-data/init_jodas.xml")
public class JodaServiceTest extends ServiceTestCase {
    @Autowired
    @Qualifier("jodaServiceTx")
    private JodaService jodaService;

    @Before
    public void setUp() throws Exception {
        DateTime dateTime = new DateTime(2010, 4, 3, 8, 0, 0, 0);
        DateTimeUtils.setCurrentMillisFixed(dateTime.getMillis());
    }

    @After
    public void tearDown() throws Exception {
        DateTimeUtils.setCurrentMillisSystem();
    }

    @Test
    public void testDelete() {
        jodaService.delete(Long.valueOf(101));
    }

    @Test
    public void testInsert() {
        Joda record = new Joda();
        Date xdate = SystemClock.getDate();
        record.setXdate(xdate);
        record.setXtime(xdate);
        record.setXdatetime(xdate);
        record.setXtimestamp(xdate);
        jodaService.insert(record);
    }

    @Test
    public void testGet() {
        Joda selectByPrimaryKey = jodaService.get(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);

        DateTime xdate = new DateTime(2010, 4, 3, 0, 0, 0, 0);
        DateTime xtime = new DateTime(1970, 1, 1, 8, 0, 0, 0);
        DateTime xdatetime = new DateTime(2010, 4, 3, 8, 0, 0, 0);
        DateTime xtimestamp = new DateTime(2010, 4, 3, 8, 0, 0, 0);

        assertEquals(xdate.toDate(), selectByPrimaryKey.getXdate());
        assertEquals(xtime.toDate(), selectByPrimaryKey.getXtime());
        assertEquals(xdatetime.toDate(), selectByPrimaryKey.getXdatetime());
        assertEquals(xtimestamp.toDate(), selectByPrimaryKey.getXtimestamp());
    }

    @Test
    public void testUpdate() {
        Joda record = new Joda();
        record.setId(Long.valueOf(103));
        Date xdate = SystemClock.getDate();
        record.setXdate(xdate);
        record.setXtime(xdate);
        record.setXdatetime(xdate);
        record.setXtimestamp(xdate);
        jodaService.update(record, true);
    }

    @Test
    public void testFind() {
        JodaSearch search = new JodaSearch();
        DateTime dateTime = new DateTime(2010, 4, 4, 0, 0, 0, 0);
        search.setXdate(dateTime.toDate());
        List<Joda> find = jodaService.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        JodaSearch search = new JodaSearch();
        DateTime dateTime = new DateTime(2010, 4, 4, 0, 0, 0, 0);
        search.setXdate(dateTime.toDate());
        Long count = jodaService.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        JodaSearch search = new JodaSearch();
        DateTime dateTime = new DateTime(2010, 4, 4, 0, 0, 0, 0);
        search.setXdate(dateTime.toDate());
        Page page = new Page();
        List<Joda> listUser = jodaService.page(search, page);
        assertEquals(3, listUser.size());
    }

}
