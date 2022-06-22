package de.lmu.client;

import de.lmu.server.api.CallbackIF;
import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.api.Bericht;
import de.lmu.server.api.Roentgenbild;

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

            CallbackIF callbackEmpfänger = new CallbackEmpfänger();
            CallbackIF callbackEmpfängerStub = (CallbackIF) UnicastRemoteObject.exportObject(callbackEmpfänger, 0);

            // eigentliche "remote"-Aufruf
            stub.analysiere(roentgenbild, callbackEmpfängerStub);

            System.out.println("Analyse wurde gestartet");


        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
