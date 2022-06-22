package SS2016.Uebung;

import de.lmu.Bericht;
import de.lmu.FrueherkennungIF;
import de.lmu.Roentgenbild;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;



public class FrueherkennungServer implements FrueherkennungIF {
    
    private static int nummerLetzerBericht = 0;

    @Override
    public Bericht analysieren(Roentgenbild bild) throws RemoteException {
        
        System.out.println("Roentgenbild vom " + bild.getAufnahmeVom() + " wurde analysiert!");
        return new Bericht( new Date(), "alles okay [Bericht Nr. " + ++nummerLetzerBericht + "]", "Neue Vorsorgeuntersuchung in 2 Jahren erbeten");
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
            Registry verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);

            // Stub unter logischem Namen ein Registry eintragen (Stub wird serialisiert und dorthin als Kopie übertragen)
            verzeichnisdienst.bind("LMU-Frueherkennungs-Service", stub);

        } catch (AlreadyBoundException | AccessException ex) {
            ex.printStackTrace();
        }
    }
}
