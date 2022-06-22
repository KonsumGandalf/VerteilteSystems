package Development.uebung06.byReference;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class FrueherkennungServer implements FrueherkennungIF {
    @Override
    public BerichtIF analysieren(RoentgenbildIF roentgenbildIF){
        BerichtIF clientStub;
        try {
            System.out.println("Empfangen: "+roentgenbildIF.getRepresentation());
            Bericht bericht = new Bericht("CoronaBier", "Mach dir gar kein Stress");
            clientStub = (BerichtIF) UnicastRemoteObject.exportObject(bericht, 187);

            System.out.println("Sende: "+clientStub.getRepresentation());
            return clientStub;

        } catch (RemoteException  e) {
            e.printStackTrace();
            return null;
        }
    }
}
