package de.lmu.server;

import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.impl.FrueherkennungService;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Start {

    public static void main(String[] args) {
        FrueherkennungIF serverImplementierung = new FrueherkennungService();
        try {
            FrueherkennungIF stub = (FrueherkennungIF) UnicastRemoteObject.exportObject(serverImplementierung, 0); // Stub und Skeleton gebaut
            // Registry registry = LocateRegistry.getRegistry(1099);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Frueherkennungs-Dienst", stub);
            System.out.println("Server hochgefahren, warte auf Anfragen");

        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
