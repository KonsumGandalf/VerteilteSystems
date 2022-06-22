package de.oth.vs.rest;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.NetworkConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URISyntaxException;
import java.util.Arrays;
import javax.swing.JOptionPane;
import javax.ws.rs.ext.RuntimeDelegate;
import org.glassfish.jersey.server.ResourceConfig;

public class ServerREST {
    
    static HazelcastInstance hazelcast = null;
    
    public static void main(String[] args) throws IllegalArgumentException, IOException, URISyntaxException {
        
        // JAX-RS bzw. Jersey konfigurieren...
        ResourceConfig config = new ResourceConfig();
        // ... indem lediglich Klasse(n) registriert werden, die @Path-Annotationen haben (hier nur eine)
        config.register(StudentResource.class);
        config.register(PruefungsleistungResource.class);
        config.register(VeranstaltungResource.class);

        // Webserver für Port 8080 generieren (Teil des JDK!)
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        // Handler erzeugen (Handler = Objekt, das Request bekommt und Response erzeugt)
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(config, HttpHandler.class);
        // Webserver den Handler (oben) einem bestimmten Kontext zuordnen
        // Der Handler ist jetzt zuständig für alle Requests, die mit http://localhost:8080/webresources beginnen
        server.createContext("/webresources", handler);
        // Webserver starten...
        server.start();
        
        
        // Hazelcast-Instanz erzeugen:
        //    Netzwerk-Configuration erstellen
        Config hazelcastConfig = new Config();
        hazelcastConfig.getNetworkConfig().setPortAutoIncrement( true );
        NetworkConfig networkConfig = hazelcastConfig.getNetworkConfig();
        networkConfig.getInterfaces().setEnabled(true);
        // Setzen Sie dieses Interface auf den Adressbereich Ihres Netzwerks
        // Fuer die OTH bitte auf 172.*.*.* setzen
        networkConfig.getInterfaces().setInterfaces(Arrays.asList("172.*.*.*"));
        JoinConfig joinConfig = networkConfig.getJoin();
        joinConfig.getMulticastConfig().setEnabled( true );
        joinConfig.getMulticastConfig().setMulticastGroup("224.2.2.3");
        joinConfig.getMulticastConfig().setMulticastPort(54327);
        //    Neue Hazelcast-Instanz erzeugen
        hazelcast = Hazelcast.newHazelcastInstance(hazelcastConfig);
        
        // Dialog anzeigen (blockierend)
        JOptionPane.showMessageDialog(null, "Server stoppen...");
        
        // Jersey-Server und Hazelcast-Node herunterfahren
        server.stop(0);
        hazelcast.shutdown();
    }
}
