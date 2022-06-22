package Development.uebung03.b_threadpool_webserver_2;

import java.io.*;
import java.nio.file.NoSuchFileException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable{

    private final Socket clientRequest;
    RequestHandler(Socket clientRequest){
        this.clientRequest = clientRequest;
    }
    public void run() {
        PrintWriter writer = null;
        try {
            /*            InputStream in = this.clientRequest.getInputStream();
                        BufferedReader reader2 = new BufferedReader(new InputStreamReader(in));
                        OutputStream out = this.clientRequest.getOutputStream();
                        writer = new PrintWriter(out);
                        writer.println("Hallo"); // oder print(("Hallo\n");
                        writer.flush();
                        String antwort = reader2.readLine();
                        System.out.println("Antwort der Gegenseite: " + antwort);*/

            BufferedReader reader = new BufferedReader(new InputStreamReader(this.clientRequest.getInputStream()));
            writer = new PrintWriter(clientRequest.getOutputStream());

            String request = reader.readLine();
            String[] requestParts = request.split(" ");
            String fileName = "index.html"; // requestParts[1];

            System.out.println("Angeforderte Datei: " + fileName);
            // Alle Zeilen der Datei einlesen:
            // (Relativer Pfad in NetBeans-Projekten immer von Projektverzeichnis aus)
            Path path = Paths.get("./src", fileName);
            System.out.println("path1 = " + path);
            List<String> linesInFile = Files.readAllLines(path);

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
        } catch (IOException ex) {
            System.out.print("Fehler in Requestverarbeitung: " + ex.getClass().getSimpleName() + " : " + ex.getMessage());
        }
    }

}
