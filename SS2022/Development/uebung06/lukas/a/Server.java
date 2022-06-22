package Development.uebung06.lukas.a;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server implements FrueherkennungIF {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        FrueherkennungIF frueherkennungsserver = new Server();
        FrueherkennungIF stub = (FrueherkennungIF) UnicastRemoteObject.exportObject(frueherkennungsserver, 0);

        Registry r = LocateRegistry.createRegistry(1212);
        r.bind("Test", stub);

    }


    @Override
    public Bericht analysieren(RoentgenbildIF rb) throws RemoteException {
        rb.setPatientenName("Thomas");
        Bericht bericht = new Bericht("Krebs", "Chemo");
        return bericht;
    }


}
