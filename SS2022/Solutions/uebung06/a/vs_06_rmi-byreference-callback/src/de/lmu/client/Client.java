package de.lmu.client;

import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.api.Bericht;
import de.lmu.server.api.RoentgenbildIF;
import de.lmu.server.entity.Roentgenbild;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {

    public static void main(String[] args) {
        System.out.println("Früherkennung starten");

        Roentgenbild roentgenbild = new Roentgenbild();
        roentgenbild.setPatientenName("Lisa Muster");

        try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);
            FrueherkennungIF stub = (FrueherkennungIF) registry.lookup("Frueherkennungs-Dienst");

            // Stub für das Röntgenbild erzeugen
            RoentgenbildIF roentgenbildStub = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenbild, 0);

            // eigentliche "remote"-Aufruf
            Bericht bericht = stub.analysiere(roentgenbildStub);

            System.out.println("Bericht empfangen: " + bericht);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
