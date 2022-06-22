package Solutions.uebung03.src.b.Webserver.src;

import java.io.BufferedReader;
import java.nio.file.NoSuchFileException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


public class RequestHandler implements Runnable {

    private final Socket clientRequest;

    RequestHandler(Socket clientRequest) {
        this.clientRequest = clientRequest;
    }

    @Override
    public void run() {
        PrintWriter writer = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientRequest.getInputStream()));
            writer = new PrintWriter(clientRequest.getOutputStream());
            
            String request = reader.readLine();
            String[] requestParts = request.split(" ");
            String fileName = requestParts[1];
            
            System.out.println("Angeforderte Datei: " + fileName);
            
            // Alle Zeilen der Datei einlesen:
            // (Relativer Pfad in NetBeans-Projekten immer von Projektverzeichnis aus)
            List<String> linesInFile = Files.readAllLines(Paths.get("./src" + fileName));
            
            // Alle Zeilen plus Header-Zeilen in Socket schreiben:
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html");
            writer.print("Content-Length: ");
            // Lambda-Ausdruck (seit Java 8 möglich): zählt hier die Zeichen im File
            // (geht auch herkömmlich mit Iteration über Zeilen und sum += zeile.length()+1)
            writer.println("" + linesInFile.stream().mapToInt( line -> line.length()+1 ).sum());
            // Leere Zeile zwischen Header und Body wichtig
            writer.println("");
            // Inhalt der Datei zeilenweise in Socket schreiben
            for(String zeile : linesInFile ) {
                writer.println(zeile);
            }
            writer.flush();
            clientRequest.close();
            
        } catch (NoSuchFileException ex) {
            System.out.println("File not found!");
            writer.println("HTTP/1.1 404 Not found"); // File not found
            writer.println("Content-Type: text/html");
            writer.println("Content-Length: 22");
            writer.println();
            writer.println("Datei nicht gefunden!");
            writer.flush();
            writer.close();
        }
        catch (IOException ex) {
            System.out.print("Fehler in Requestverarbeitung: " + ex.getClass().getSimpleName() + " : " + ex.getMessage());
        }
        
        try {
            clientRequest.close();
        } catch (IOException ex) {
            // 
        }
    }
    
}
