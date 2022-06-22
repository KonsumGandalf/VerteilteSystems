package de.lmu;

import de.lmu.server.entity.Bericht;
import de.lmu.server.entity.Roentgenbild;

import java.rmi.RemoteException;

public class FrueherkennungService implements FrueherkennungIF {

    @Override
    public Bericht analysiere(Roentgenbild roentgenbild) throws RemoteException {
        // eigentliche Server-Implementierung
        System.out.println("Empfangen: " + roentgenbild.toString());

        Bericht antwort = new Bericht();
        antwort.setDiagnose("Schnupfen");
        antwort.setWeiteresVorgehen("Eine Woche Bettruhe und VS-Videos");

        System.out.println("Sende: " + antwort);

        return antwort;
    }
}
