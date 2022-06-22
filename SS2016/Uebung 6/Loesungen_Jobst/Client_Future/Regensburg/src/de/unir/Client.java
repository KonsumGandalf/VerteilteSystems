package SS2016.Uebung;

import de.lmu.Bericht;
import de.lmu.FrueherkennungIF;
import de.lmu.Roentgenbild;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Client {

    public static void main(String[] args) throws InterruptedException {
        try {
            Registry verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);

                // Stub von Registry laden
                FrueherkennungIF server = (FrueherkennungIF) verzeichnisdienst.lookup("LMU-Frueherkennungs-Service-Future");
                // Parameter erzeugen (wird dann serialisiert und by-value an den Server uebertragen)
                Roentgenbild roentgenbild = new Roentgenbild( "Max Muster", new Date() );

                // Methode gegen Stub aufrufen; Aufruf erfolgt remote
                Future<Bericht> berichtFuture = server.analysieren(roentgenbild);
                
                boolean berichtFertig = false;
                do {
                    TimeUnit.SECONDS.sleep(1);
                    berichtFertig = berichtFuture.isDone();
                    System.out.println("Bericht liegt noch nicht vor. Warte 1 Sek. ...");
                } while(!berichtFertig);
                
                Bericht bericht;
                try {
                    bericht = berichtFuture.get();
                } catch (ExecutionException ex) {
                    System.out.println("Fehler");
                    return;
                }
                
                // Bericht wurde vom Server serialisiert und by-value zurück übertragen
                System.out.println("Bericht empfangen mit Datum="+ bericht.getDatum() + " und Diagnose=" + bericht.getDiagnose() + " und Vorgehen=" + bericht.getWeiteresVorgehen());

        } catch (NotBoundException | AccessException ex) {
            ex.printStackTrace();
        } catch (RemoteException ex) {
            ex.printStackTrace();
        }
    }
    
}
