package Development.uebung06.byValue;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {

        System.out.println("Frueherkennung starten");
        Roentgenbild roentgenbild = new Roentgenbild("Ron");

        try{
            Registry r = LocateRegistry.getRegistry("localhost", 187);
            FrueherkennungIF frueherkennungServer = (FrueherkennungIF) r.lookup("Frueherkennung");
            Bericht antwortBericht = frueherkennungServer.analysieren(roentgenbild);
            System.out.println("Bericht empfangen: " + antwortBericht);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
