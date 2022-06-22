package de.lmu;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Früherkennungsserver implements FrüherkennungIF{

    @Override
    public BerichtIF analysieren(Röntgenbild bild) throws RemoteException {

        Bericht b = new Bericht(null,"Gehirntumor","Chemo");

        BerichtIF berichtStub = (BerichtIF) UnicastRemoteObject.exportObject(b,0);
        return berichtStub;
    }

    public static void main(String[] args) throws AlreadyBoundException, RemoteException {
        FrüherkennungIF erkennung = new Früherkennungsserver();
        FrüherkennungIF stub = (FrüherkennungIF) UnicastRemoteObject.exportObject(erkennung,0);
        Registry r = LocateRegistry.createRegistry(1099);
        r.bind("analysieren",stub);
    }
}
