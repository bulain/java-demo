package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.User;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_users.xml")
public class NativeNamedQueryTest extends HibernateTestCase {

    @Test
    public void testNativeQueryFind() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");

        List<User> list = session.getNamedQuery("User_native_find").setProperties(search).list();
        assertEquals(3, list.size());
    }

    @Test
    public void testNativeQueryCount() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");

        Number cnt = (Number) session.getNamedQuery("User_native_count").setProperties(search).uniqueResult();
        assertEquals(3L, cnt.longValue());
    }

    @Test
    public void testNativeQueryPage() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");

        List<User> list = session.getNamedQuery("User_native_find").setProperties(search).setFirstResult(0)
                .setMaxResults(10).list();
        assertEquals(3, list.size());
    }

}
