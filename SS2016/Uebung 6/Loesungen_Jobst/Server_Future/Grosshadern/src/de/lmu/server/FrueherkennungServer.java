package SS2016.Uebung;

import de.lmu.BerichtFuture;
import de.lmu.AnalyseIF;
import de.lmu.Bericht;
import de.lmu.FrueherkennungIF;
import de.lmu.Roentgenbild;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class FrueherkennungServer implements FrueherkennungIF {
    
    private static Registry verzeichnisdienst;
    private ExecutorService threadPool = Executors.newCachedThreadPool();

    @Override
    public Future<Bericht> analysieren(Roentgenbild bild) throws RemoteException {
        
        System.out.println("Roentgenbild vom " + bild.getAufnahmeVom() + " wurde empfangen und wird spaeter analysiert!");
        
        // Dediziertes Analyseobjekt (Thread/Runnable) fuer jede Analyseanfrage
        Analyse analyse = new Analyse(bild);
        
        // Analyse ist Server-Stub fuer das zurueckgegebene Future-Objekte
        // (Future hat intern Stub, um mit dieser Instanz zu kommunizieren, deshalb exportObject...)
        AnalyseIF analyseStub = (AnalyseIF) UnicastRemoteObject.exportObject(analyse, 0);
        
        // Statt new Thread(analyse).start() besser ueber einen ThreadPool (s. o.)
        threadPool.execute(analyse);
        
        // Nicht auf Ende der Analyse warten sondern Future<Bericht>-Objekt zurueckgeben
        return new BerichtFuture(analyseStub);
    }
    
    
    public static void main(String[] args) throws RemoteException {
        try {
            // Ein Objekt erzeugen, das als Serverinstanz fungiert
            FrueherkennungIF server = new FrueherkennungServer();

            // Stub aus obigem Objekt erzeugen und Stub bei im RMI-Subsystem der JVM eintragen
            FrueherkennungIF stub   = (FrueherkennungIF) UnicastRemoteObject.exportObject(server, 0);

            // Registryinstanz auf dem localhost starten
            LocateRegistry.createRegistry(1099); // alternativ über Konsole starten: $ > rmiregistry --classpath=...

            // Verbindung zur Registry aufbauen
            verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);

            // Stub unter logischem Namen ein Registry eintragen (Stub wird serialisiert und dorthin als Kopie übertragen)
            verzeichnisdienst.bind("LMU-Frueherkennungs-Service-Future", stub);
            
            System.out.println("Server gestartet (branch future)");

        } catch (AlreadyBoundException | AccessException ex) {
            ex.printStackTrace();
        }
    }
}
