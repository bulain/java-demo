package com.bulain.jbpm4order.controller;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.bulain.common.dataset.SeedDataSet;
import com.bulain.common.model.Profile;
import com.bulain.common.page.Page;
import com.bulain.common.pojo.ProfileView;
import com.bulain.common.test.ActionTestCase;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionProxy;

@SeedDataSet(file = "data/init_action.xml")
public class ProfileActionTest extends ActionTestCase {
    @Before
    public void setUp() throws Exception {
        super.setUp();
        super.setUpAction("admin", "admin");
    }

    @After
    public void tearDown() throws Exception {
        super.tearDownAction();
        super.tearDown();
    }

    @Test
    public void testCURD() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/profile/new");
        ProfileAction profileAction = (ProfileAction) proxy.getAction();
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("profile.personId", "1");
        request.setParameter("profile.language", "language");
        request.setParameter("profile.country", "country");

        proxy = getActionProxy("/profile/create");
        profileAction = (ProfileAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        proxy = getActionProxy("/profile/list");
        profileAction = (ProfileAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        List<ProfileView> listProfile = profileAction.getListProfile();
        assertEquals(1, listProfile.size());
        Page page = profileAction.getPage();
        assertEquals(1, page.getPage());

        Long id = listProfile.get(0).getId();

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/profile/edit");
        profileAction = (ProfileAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        Profile profile = profileAction.getProfile();
        assertEquals(Long.valueOf(1), profile.getPersonId());
        assertEquals("language", profile.getLanguage());
        assertEquals("country", profile.getCountry());

        initServletMockObjects();
        request.setParameter("profile.id", Long.toString(id));
        request.setParameter("profile.personId", "1");
        request.setParameter("profile.language", "language-updated");
        request.setParameter("profile.country", "country-updated");
        proxy = getActionProxy("/profile/update");
        profileAction = (ProfileAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/profile/show");
        profileAction = (ProfileAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
        profile = profileAction.getProfile();
        assertEquals(Long.valueOf(1), profile.getPersonId());
        assertEquals("language-updated", profile.getLanguage());
        assertEquals("country-updated", profile.getCountry());

        initServletMockObjects();
        request.setParameter("id", Long.toString(id));
        proxy = getActionProxy("/profile/destroy");
        profileAction = (ProfileAction) proxy.getAction();
        result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }
}
