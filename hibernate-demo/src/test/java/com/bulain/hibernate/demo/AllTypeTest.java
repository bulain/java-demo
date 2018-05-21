package com.bulain.hibernate.demo;

import java.util.Currency;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.common.util.SystemClock;
import com.bulain.hibernate.entity.Alltype;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
public class AllTypeTest extends HibernateTestCase {
    @Test
    public void testAllType() {
        Alltype allType = new Alltype();
        allType.setFvarchar("fvarchar");
        allType.setFdate(SystemClock.getDate());
        allType.setFdatetime(SystemClock.getDate());
        allType.setFint(100);
        allType.setFbitint(100000L);
        allType.setFfload(10.11f);
        allType.setFdouble(1000.333d);
        allType.setFtimezone(TimeZone.getDefault());
        allType.setFcurrency(Currency.getInstance(Locale.CHINA));
        allType.setFlocale(Locale.CHINA);
        session.save(allType);
    }
}
