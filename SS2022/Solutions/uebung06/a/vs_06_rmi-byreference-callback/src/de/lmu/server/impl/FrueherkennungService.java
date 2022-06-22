package de.lmu.server.impl;

import de.lmu.server.api.Bericht;
import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.api.RoentgenbildIF;
import de.lmu.server.entity.Roentgenbild;

import java.rmi.RemoteException;

public class FrueherkennungService implements FrueherkennungIF {

    @Override
    public Bericht analysiere(RoentgenbildIF roentgenbild) throws RemoteException {
        // eigentliche Server-Implementierung
        System.out.println("Empfangen : " + roentgenbild.toString());
        System.out.println("Empfangen2: " + roentgenbild.getAufnameDatum());

        Bericht antwort = new Bericht();
        antwort.setDiagnose("Schnupfen");
        antwort.setWeiteresVorgehen("Eine Woche Bettruhe und VS-Videos");

        System.out.println("Sende: " + antwort);

        return antwort;
    }
}
