package de.othr.vs.rest;

import com.sun.net.httpserver.HttpServer;
import de.othr.vs.rest.service.XyzService;
import jakarta.ws.rs.core.UriBuilder;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.swing.*;
import java.net.URI;


public class Server {

    public static final int PORT = 8081;

    public static void main(String[] args) {

        // Webserver-Instanz konfigurieren und starten
        // (Methode createHttpServer erzeugt und startet (!) Server)
        URI baseUri = UriBuilder.fromUri("http://localhost/api/v1").port(PORT).build();
        ResourceConfig config2 = new ResourceConfig(
                XyzService.class /* , AbcService.class  */  // Liste ggf. ergänzen mit weiteren Service-Klassen
                );                                                   // ggf. ExceptionMapper-Klasse für eigene Fehlermeldungsnummern
        HttpServer server = JdkHttpServerFactory.createHttpServer(baseUri, config2);

        System.out.println("Server auf Port " + PORT + " gestartet...");

        // Server nicht durch Ende der main-Methode sofort wieder beenden
        JOptionPane.showMessageDialog(null, "Server stoppen...");
        server.stop(0);

    }
}
