package de.oth.vs.rest;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import javax.swing.JOptionPane;
import javax.ws.rs.ext.RuntimeDelegate;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerREST {
    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException {
        
        // JAX-RS bzw. Jersey konfigurieren...
        ResourceConfig config = new ResourceConfig();
        // ... indem lediglich Klasse(n) registriert werden, die @Path-Annotationen haben (hier nur eine)
        config.register(StudentResource.class);

        // Webserver für Port 8080 generieren (Teil des JDK!)
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        // Handler erzeugen (Handler = Objekt, das Request bekommt und Response erzeugt)
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
        // Webserver den Handler (oben) einem bestimmten Kontext zuordnen
        // Der Handler ist jetzt zuständig für alle Requests, die mit http://localhost:8080/webresources beginnen
        server.createContext("/webresources", handler);
        // Webserver starten...
        server.start();

        // Dialog anzeigen (blockierend)
        JOptionPane.showMessageDialog(null, "Server stoppen...");
        server.stop(0);
    }
}
