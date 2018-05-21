package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.Person;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_persons.xml")
public class AnnotationTest extends HibernateTestCase{
    @Test
    public void testCriteria() {
        List<Person> list = session.createCriteria(Person.class).add(Restrictions.eq("firstName", "first_name_page"))
                .add(Restrictions.eq("lastName", "last_name_page")).list();
        assertEquals(3, list.size());
    }

}
