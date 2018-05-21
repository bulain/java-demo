package com.bulain.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyRun {

    public static void main(String[] args) throws Exception {
        String webapp = "src/main/webapp";

        int port = 8081;
        Server server = new Server(port);

        WebAppContext context = new WebAppContext();
        context.setDescriptor(webapp + "/web.xml");
        context.setResourceBase(webapp);
        context.setContextPath("/axis-demo");
        context.setClassLoader(Thread.currentThread().getContextClassLoader());
        context.setConfigurationDiscovered(true);
        context.setParentLoaderPriority(true);

        server.setHandler(context);
        server.start();
        System.out.println("Jetty start at 0.0.0.0:" + port);
        server.join();
    }

}
