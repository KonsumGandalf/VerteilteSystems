package Development.uebung06.lukas.b;

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
    public BerichtIF analysieren(Roentgenbild rb) throws RemoteException {
        rb.setPatientenName("Thomas");
        Bericht bericht = new Bericht("Krebs", "Chemo");

        BerichtIF berichtStub = (BerichtIF) UnicastRemoteObject.exportObject(bericht, 0);

        return berichtStub;
    }


}
