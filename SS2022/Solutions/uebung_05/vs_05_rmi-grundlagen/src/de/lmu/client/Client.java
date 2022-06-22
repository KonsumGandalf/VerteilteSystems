package de.lmu.client;

import de.lmu.FrueherkennungIF;
import de.lmu.server.entity.Bericht;
import de.lmu.server.entity.Roentgenbild;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) {
        System.out.println("Früherkennung starten");

        Roentgenbild roentgenbild = new Roentgenbild();
        roentgenbild.setPatientenName("Lisa Muster");

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FrueherkennungIF stub = (FrueherkennungIF) registry.lookup("Frueherkennungs-Dienst");

            // eigentliche "remote"-Aufruf
            Bericht bericht = stub.analysiere(roentgenbild);

            System.out.println("Bericht empfangen: " + bericht);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
