package Development.uebung06.lukas.a;

import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry r = LocateRegistry.getRegistry("localhost", 1212);
        FrueherkennungIF serverStub = (FrueherkennungIF) r.lookup("Test");
        Roentgenbild rb = new Roentgenbild("Max", "Lungenbeschwerden".getBytes(StandardCharsets.UTF_8));

        System.out.println(rb.getPatientenName());

        RoentgenbildIF roentgenbildStub = (RoentgenbildIF) UnicastRemoteObject.exportObject(rb, 0);

        Bericht antwort = serverStub.analysieren(roentgenbildStub);

        System.out.println(rb.getPatientenName());

        antwort.printBericht(antwort);
    }

}
