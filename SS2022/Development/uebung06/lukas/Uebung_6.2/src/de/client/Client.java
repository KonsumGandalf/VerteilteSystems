package de.client;

import de.lmu.BerichtIF;
import de.lmu.FrueherkennungIF;
import de.lmu.Roentgenbild;

import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry r = LocateRegistry.getRegistry("localhost", 1212);
        FrueherkennungIF server = (FrueherkennungIF) r.lookup("Test");
        Roentgenbild rb = new Roentgenbild("Max", "Lungenbeschwerden".getBytes(StandardCharsets.UTF_8));

        System.out.println(rb.getPatientenName());
        BerichtIF antwort = server.analysieren(rb);
        System.out.println(rb.getPatientenName());

        antwort.printBericht();
        antwort.printBericht();
    }

}
