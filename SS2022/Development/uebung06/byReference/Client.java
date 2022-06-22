package Development.uebung06.byReference;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {
    public static void main(String[] args) {

        System.out.println("Frueherkennung starten");
        Roentgenbild roentgenbild = new Roentgenbild("Ron");

        try{
            Registry r = LocateRegistry.getRegistry("localhost", 187);

            FrueherkennungIF serverStub = (FrueherkennungIF) r.lookup("Frueherkennung");
            RoentgenbildIF berichtStub = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenbild, 0);

            BerichtIF antwortBericht = serverStub.analysieren(berichtStub);
            System.out.println("Bericht empfangen: " + antwortBericht.getRepresentation());
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
