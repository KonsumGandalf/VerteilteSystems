package de.lmu.server.impl;

import de.lmu.server.api.Bericht;
import de.lmu.server.api.CallbackIF;
import de.lmu.server.api.FrueherkennungIF;
import de.lmu.server.api.Roentgenbild;

import java.rmi.RemoteException;

public class FrueherkennungService implements FrueherkennungIF {

    @Override
    public void analysiere(Roentgenbild roentgenbild, CallbackIF callbackStub) throws RemoteException {

        System.out.println("Empfangen: " + roentgenbild.toString());

        Runnable analyseTask = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Bericht antwort = new Bericht();
                    antwort.setDiagnose("Schnupfen");
                    antwort.setWeiteresVorgehen("Eine Woche Bettruhe und VS-Videos");

                    System.out.println("Sende: " + antwort);

                    callbackStub.zustellen(antwort);
                } catch (InterruptedException | RemoteException e) {
                    e.printStackTrace();
                }
            }
        };
        // ThreadPool/Executor übergeben
        new Thread(analyseTask).start();

        // Kontrolle sofort zurückgeben ==> return;
        return;




    }
}
