package com.bulain.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRun {

    public static void main(String[] args) throws Exception {
        String webapp = "src/main/webapp";

        Server server = new Server(8081);

        WebAppContext context = new WebAppContext();
        context.setDescriptor(webapp + "/WEB-INF/web.xml");
        context.setResourceBase(webapp);
        context.setContextPath("/dubbo-demo-web");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setConfigurationDiscovered(true);
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
        System.out.println("Jetty start at 0.0.0.0:8081");
        server.join();
    }

}
