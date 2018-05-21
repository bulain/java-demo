package com.bulain.hibernate.demo;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.test.HibernateTestCase;
import com.bulain.hibernate.entity.Group;
import com.bulain.hibernate.entity.GroupPermission;
import com.bulain.hibernate.entity.GroupUser;
import com.bulain.hibernate.entity.User;

@SuppressWarnings("unchecked")
@SeedDataSet(file = "test-data/init_seed_dataset.xml")
public class CascadeTest extends HibernateTestCase{
    @Test
    public void testCascade(){
        //prepare test data 
        User user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        
        Group group = new Group();
        group.setName("name");
        
        GroupUser gu = new GroupUser();
        gu.setUser(user);
        gu.setGroup(group);
        group.getGroupUserses().add(gu);
        
        session.save(gu);
        
        List<GroupUser> list = session.createCriteria(GroupUser.class).list();
        assertEquals(1, list.size());
        
        GroupPermission gp = new GroupPermission();
        gp.setGroup(group);
        gp.setPermission("permission");
        group.getGroupPermissionses().add(gp);
        
        session.save(gp);
        
        list = session.createCriteria(GroupPermission.class).list();
        assertEquals(1, list.size());
        
        //test
        session.delete(group);
        
        //assert after delete
        list = session.createCriteria(GroupUser.class).list();
        assertEquals(0, list.size());
        list = session.createCriteria(GroupPermission.class).list();
        assertEquals(0, list.size());

    }
}
