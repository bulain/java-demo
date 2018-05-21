package com.bulain.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRun {

    public static void main(String[] args) throws Exception {
        String webapp = "src/main/webapp";

        Server server = new Server(8086);

        WebAppContext context = new WebAppContext();
        context.setDescriptor(webapp + "/WEB-INF/web.xml");
        context.setResourceBase(webapp);
        context.setContextPath("/hessian-demo");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setConfigurationDiscovered(true);
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
        System.out.println("Jetty start at 0.0.0.0:8086");
        server.join();
    }

}
