package com.bulain.cxf.jetty;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyServer {
    public static void main(String[] args) throws Exception {
    	int port = 8082;
        Server server = new Server(port);

        String webapp = "src/main/webapp";
        WebAppContext context = new WebAppContext();
        context.setDescriptor(webapp + "/WEB-INF/web.xml");
		context.setResourceBase(webapp);
		context.setContextPath("/cxf-demo");
		context.setClassLoader(Thread.currentThread().getContextClassLoader());
		context.setConfigurationDiscovered(true);
		context.setParentLoaderPriority(true);

        HandlerCollection handlers = new HandlerCollection();
        handlers.setHandlers(new Handler[]{context, new DefaultHandler()});

        server.setHandler(handlers);
        server.start();
        System.out.println("Jetty start at 0.0.0.0:" + port);
        server.join();
    }
}
