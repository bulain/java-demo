package com.bulain.jaas;


import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("unused")
public class JaasLoginModule implements LoginModule {
    private static final Logger logger = LoggerFactory.getLogger(JaasLoginModule.class);

    private Subject subject;
    private CallbackHandler callbackHandler;
    private Map<String, ?> sharedState;
    private Map<String, ?> options;

    private String username;
    private Principal principal;

    public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
            Map<String, ?> options) {
        this.subject = subject;
        this.callbackHandler = callbackHandler;
        this.sharedState = sharedState;
        this.options = options;
    }

    public boolean login() throws LoginException {
        NameCallback nameCallback = new NameCallback("User Name: ");
        PasswordCallback passwordCallback = new PasswordCallback("Password: ", false);
        Callback[] callbacks = new Callback[]{nameCallback, passwordCallback};

        try {
            callbackHandler.handle(callbacks);
        } catch (IOException e) {
            logger.error("login()", e);

            throw new LoginException(e.getMessage());
        } catch (UnsupportedCallbackException e) {
            logger.error("login()", e);

            throw new LoginException(e.getMessage());
        }

        username = nameCallback.getName();
        char[] password = passwordCallback.getPassword();
        String tempPassword = String.copyValueOf(password);
        if (!username.startsWith("username") || !tempPassword.startsWith("password")) {
            throw new FailedLoginException("User Name or Password Incorrect");
        }

        logger.debug("login() - {}: {} - {}", new Object[]{this, username, tempPassword});
        
        return true;
    }

    public boolean commit() throws LoginException {
        principal = new JaasPrincipal(username);
        subject.getPrincipals().add(principal);
        return true;
    }

    public boolean abort() throws LoginException {
        username = null;
        subject.getPrincipals().remove(principal);
        return false;
    }

    public boolean logout() throws LoginException {
        username = null;
        subject.getPrincipals().remove(principal);
        return true;
    }

}
