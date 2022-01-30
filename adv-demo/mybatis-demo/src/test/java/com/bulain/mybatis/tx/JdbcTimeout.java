package com.bulain.mybatis.tx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public class JdbcTimeout {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DataSource dataSource;
    private Date startAt;

    @Before
    public void setUp() {
        startAt = new Date();
        logger.info("setUp");
    }

    @After
    public void tearDown() {
        Date endAt = new Date();
        double during = (endAt.getTime() - startAt.getTime()) / 1000d;
        logger.info("tearDown during {}s", new Object[]{during});
    }

    @Test
    public void testTimeoutAfter() throws InterruptedException, SQLException {
        Connection conn = dataSource.getConnection();

        conn.setAutoCommit(false);
        String sql = "insert into blog (TITLE, DESCR, ACTIVE_FLAG, CREATED_VIA, REMARKS) "
                + "values ('aaa', 'aaa', 'Y', 'M', '')";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();

        Thread.sleep(120 * 1000);

        conn.commit();

    }

    @Test
    public void testTimeoutBefore() throws InterruptedException, SQLException {
        Connection conn = dataSource.getConnection();

        conn.setAutoCommit(false);

        Thread.sleep(120 * 1000);
        String sql = "insert into blog (TITLE, DESCR, ACTIVE_FLAG, CREATED_VIA, REMARKS) "
                + "values ('aaa', 'aaa', 'Y', 'M', '')";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.execute();

        conn.commit();

    }

}
