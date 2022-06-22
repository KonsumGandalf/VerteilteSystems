package de.lmu.client;

import de.lmu.server.api.BerichtIF;
import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.entity.Bericht;
import de.lmu.server.api.Roentgenbild;

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
            BerichtIF bericht = stub.analysiere(roentgenbild);

            System.out.println("Bericht empfangen: " + bericht);
            System.out.println("Diagnose : " + bericht.getDiagnose());
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
