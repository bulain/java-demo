package com.bulain.common.test;

import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsTestCase;
import org.apache.struts2.dispatcher.ApplicationMap;
import org.apache.struts2.dispatcher.SessionMap;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPageContext;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.web.context.WebApplicationContext;

import com.bulain.common.dataset.DataSetTestExecutionListener;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionProxy;

@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class, DataSetTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext*.xml"})
public abstract class ActionTestCase extends StrutsTestCase implements ApplicationContextAware {
    protected ApplicationContext applicationContext;

    public final void setApplicationContext(final ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // ///////////////////////////////////////////////struts
    protected void setupBeforeInitDispatcher() throws Exception {
        servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, applicationContext);
    }

    protected void initServletMockObjects() {
        if (servletContext == null) {
            servletContext = new MockServletContext(resourceLoader);
        }
        response = new MockHttpServletResponse();
        request = new MockHttpServletRequest(servletContext);
        pageContext = new MockPageContext(servletContext, request, response);
    }

    @SuppressWarnings("unchecked")
    protected ActionProxy getActionProxy(String uri) {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Map<String, Object> application = ActionContext.getContext().getApplication();

        ActionProxy proxy = super.getActionProxy(uri);

        ServletContext context = ServletActionContext.getServletContext();
        session = (session == null ? new SessionMap<String, Object>(request) : session);
        application = (application == null ? new ApplicationMap(context) : application);

        ActionContext.getContext().setSession(session);
        ActionContext.getContext().setApplication(application);

        return proxy;
    }

    protected void setUpAction(String loginName, String password) throws Exception {
        initServletMockObjects();
        request.addParameter("login.loginName", loginName);
        request.addParameter("login.hashedPassword", password);
        ActionProxy proxy = getActionProxy("/authenticate/logon");
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }

    protected void tearDownAction() throws Exception {
        initServletMockObjects();
        ActionProxy proxy = getActionProxy("/authenticate/logout");
        String result = proxy.execute();
        assertEquals(Action.SUCCESS, result);
    }
}
