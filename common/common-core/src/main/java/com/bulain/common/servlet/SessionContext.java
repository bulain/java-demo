package com.bulain.common.servlet;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;

public final class SessionContext {
    private static final Map<String, HttpSession> SESSION_MAP = new ConcurrentHashMap<String, HttpSession>();

    private SessionContext() {
    }

    public static void addSession(HttpSession session) {
        SESSION_MAP.put(session.getId(), session);
    }
    public static void removeSession(HttpSession session) {
        SESSION_MAP.remove(session.getId());
    }
    public static HttpSession getSession(String sid) {
        return SESSION_MAP.get(sid);
    }
}
