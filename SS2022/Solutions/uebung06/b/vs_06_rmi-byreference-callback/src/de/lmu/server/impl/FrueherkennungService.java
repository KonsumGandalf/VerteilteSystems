package de.lmu.server.impl;

import de.lmu.server.api.BerichtIF;
import de.lmu.server.entity.Bericht;
import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.api.Roentgenbild;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class FrueherkennungService implements FrueherkennungIF {

    @Override
    public BerichtIF analysiere(Roentgenbild roentgenbild) throws RemoteException {
        // eigentliche Server-Implementierung
        System.out.println("Empfangen: " + roentgenbild.toString());

        Bericht antwort = new Bericht();
        antwort.setDiagnose("Schnupfen");
        antwort.setWeiteresVorgehen("Eine Woche Bettruhe und VS-Videos");

        BerichtIF berichtStub = (BerichtIF) UnicastRemoteObject.exportObject(antwort, 0);

        System.out.println("Sende: " + antwort);

        return berichtStub;
    }
}
