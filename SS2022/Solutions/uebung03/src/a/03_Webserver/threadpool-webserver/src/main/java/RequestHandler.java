
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
            System.out.println("akt. Pfad:" + Paths.get(".").toAbsolutePath().toString());
            List<String> linesInFile = Files.readAllLines(Paths.get("./src/main/resources" + fileName));
            // Alle Zeilen plus Header-Zeilen in Socket schreiben:
            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/html");
            writer.print("Content-Length: ");
            // Lambda-Ausdruck: zählt hier die Zeichen im File
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
            writer.println("HTTP/1.1 404 Not found"); // File not found
            writer.println("Content-Type: text/html");
            writer.println("Content-Length: 34");
            writer.println();
            writer.println("Fehler 404: Datei nicht gefunden!");
            writer.flush();
            writer.close();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        
        try {
            clientRequest.close();
        } catch (IOException ex) {
            // 
        }
    }
    
}
