package com.bulain.jaas;

import static org.junit.Assert.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

import org.junit.Test;

public class JaasDemo {
    @Test
    public void testJaas() throws LoginException {
        String username = "username";
        String password = "password";
        runJaas(username, password);
    }

    private void runJaas(String username, String password) throws LoginException {
        JaasCallbackHandler jaasCallbackHandler = new JaasCallbackHandler(username, password);
        LoginContext loginContext = new LoginContext("JaasDemo", jaasCallbackHandler);

        Subject subject;
        Set<Principal> principals;
        Principal principal;

        loginContext.login();
        subject = loginContext.getSubject();
        principals = subject.getPrincipals();
        assertEquals(1, principals.size());
        principal = new JaasPrincipal(username);
        assertTrue(principals.contains(principal));

        loginContext.logout();
        subject = loginContext.getSubject();
        principals = subject.getPrincipals();
        assertEquals(0, principals.size());
        principal = new JaasPrincipal(username);
        assertFalse(principals.contains(principal));
    }

    @Test
    public void testJaasPt() throws InterruptedException, ExecutionException {
        List<Future<Object>> listFeture = new ArrayList<Future<Object>>();
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 20; i++) {
            String username = String.format("username%d", i);
            String password = String.format("password%d", i);
            ThreadCallable callable = new ThreadCallable(new JaasDemo(), username, password);
            Future<Object> future = executorService.submit(callable);
            listFeture.add(future);
        }

        for (Future<Object> future : listFeture) {
            future.get();
        }

        executorService.shutdownNow();
    }

    class ThreadCallable implements Callable<Object> {
        private JaasDemo demo;
        private String username;
        private String password;

        public ThreadCallable(JaasDemo demo, String username, String password) {
            this.demo = demo;
            this.username = username;
            this.password = password;
        }

        public Object call() throws Exception {
            demo.runJaas(username, password);
            return demo;
        }

    }
}
