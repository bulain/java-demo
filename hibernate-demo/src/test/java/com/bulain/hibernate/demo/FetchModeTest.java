package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.Group;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_groups.xml")
public class FetchModeTest extends HibernateTestCase {

    @Test
    public void testFetchModeDefault() {
        Group search = new Group();
        search.setName("name_page");

        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(Group.class).setFetchMode("groupUserses", FetchMode.DEFAULT)
                .add(example);

        List<Group> find = dc.getExecutableCriteria(session).addOrder(Order.asc("name")).list();
        for (Group grp : find) {
            Hibernate.initialize(grp.getGroupPermissionses());
            Hibernate.initialize(grp.getGroupUserses());
        }
        assertEquals(3, find.size());
    }

    @Test
    public void testFetchModeJoin() {
        Group search = new Group();
        search.setName("name_page");

        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(Group.class).setFetchMode("groupUserses", FetchMode.JOIN)
                .add(example);

        List<Group> find = dc.getExecutableCriteria(session).addOrder(Order.asc("name")).list();
        for (Group grp : find) {
            Hibernate.initialize(grp.getGroupPermissionses());
            Hibernate.initialize(grp.getGroupUserses());
        }
        assertEquals(3, find.size());
    }

    @Test
    public void testFetchModeSelect() {
        Group search = new Group();
        search.setName("name_page");

        Example example = Example.create(search);
        DetachedCriteria dc = DetachedCriteria.forClass(Group.class).setFetchMode("groupUserses", FetchMode.SELECT)
                .add(example);

        List<Group> find = dc.getExecutableCriteria(session).addOrder(Order.asc("name")).list();
        for (Group grp : find) {
            Hibernate.initialize(grp.getGroupPermissionses());
            Hibernate.initialize(grp.getGroupUserses());
        }
        assertEquals(3, find.size());
    }
}
