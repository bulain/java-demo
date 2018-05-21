package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.junit.Test;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.User;
import com.bulain.hibernate.pojo.UserSearch;
import com.bulain.hibernate.util.FreemarkerUtil;

import freemarker.template.TemplateException;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
@DataSet(file = "test-data/init_users.xml")
public class DynamicQueryTest extends HibernateTestCase {
    
    @Test
    public void testDynamicQuery() throws IOException, TemplateException, HibernateException {
        UserSearch search = new UserSearch();
        search.setFirstName("first_name_page");
        search.setLastName("last_name_page");
        search.setOrderBy("firstName");
        search.setSequance("desc");
        
        Query namedQuery = session.getNamedQuery("com.bulain.hibernate.entity.User.dynamicFind");
        String queryString = namedQuery.getQueryString();
        String hql = FreemarkerUtil.formatHQL(queryString, search);
        Query query = session.createQuery(hql);
        
        List<User> list = query.setProperties(search).list();
        assertEquals(3, list.size());
    }
    
}
