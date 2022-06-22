package SS2016.Uebung;

import de.lmu.Bericht;
import de.lmu.FrueherkennungIF;
import de.lmu.RoentgenbildIF;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;


public class Client {

    public static void main(String[] args) {
        System.out.println("in main");
        try {
            Registry verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);

            // Stub von Registry laden
            FrueherkennungIF server = (FrueherkennungIF) verzeichnisdienst.lookup("LMU-Frueherkennungs-Service");

            // Parameter erzeugen: das ist jetzt ein Remote-Objekt, d. h. auch ein "kleiner" RMI-Server...
            RoentgenbildIF roentgenbild = new Roentgenbild( "Max Muster", new Date() );
            // ...deshalb davon einen Stub erzeugen und bei RMI-Subsystem der JVM registriegen
            RoentgenbildIF roentgenbildStub = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenbild, 0);

            // Methode gegen Stub aufrufen; Aufruf erfolgt remote;
            // Roentgenbild wird jetzt nicht serialisiert sonder der Stub uebertragen
            System.out.println("now!!!");
            Bericht bericht = server.analysieren(roentgenbildStub);

            // Bericht wurde vom Server serialisiert und by-value zurück übertragen
            System.out.println("Bericht empfangen mit Datum="+ bericht.getDatum() + " und Diagnose=" + bericht.getDiagnose() + " und Vorgehen=" + bericht.getWeiteresVorgehen());

            // Client wird an dieser Stelle nicht beendet (Java-Prozess besteht weiterhin),
            // da ja noch Aufrufe auf den roentgenbildStub erfolgen werden...
            
        } catch (NotBoundException | AccessException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
