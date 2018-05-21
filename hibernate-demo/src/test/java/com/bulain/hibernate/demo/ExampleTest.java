package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.User;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_users.xml")
public class ExampleTest extends HibernateTestCase {

    @Test
    public void testExample() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");

        Example example = Example.create(search);
        
        Criteria criteria = session.createCriteria(User.class)
            .add(example);
        List<User> list = criteria.list();

        assertEquals(3, list.size());
    }

    @Test
    public void testPropertyExample() {
        User search = new User();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");

        Example example = Example.create(search)
            //.excludeZeroes()
            //.excludeProperty("lastName")
            .ignoreCase()
            .enableLike(MatchMode.START);

        Criteria criteria = session.createCriteria(User.class)
            .add(example);

        List<User> list = criteria.list();

        assertEquals(3, list.size());
    }
}
