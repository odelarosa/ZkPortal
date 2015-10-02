package com.delarosa.portal;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.webapp.WebAppContext;

/**
 *
 * @author odelarosa
 */
public class JettyZk {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Server server = new Server(8080);

            // Handler for multiple web apps
            HandlerCollection handlers = new HandlerCollection();

            // Creating the first web application context
            WebAppContext webcontext = new WebAppContext();
            webcontext.setResourceBase("src/com/delarosa/portal/web/");
            webcontext.setDefaultsDescriptor("src/com/delarosa/portal/webdefault.xml");

            ResourceHandler handler = new ResourceHandler();
            handler.setResourceBase("src/com/delarosa/portal/web/css/");

            handlers.addHandler(handler);
            handlers.addHandler(webcontext);
            handlers.addHandler(new DefaultHandler());

            // Adding the handlers to the server
            server.setHandler(handlers);

            // Starting the Server
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(JettyZk.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
