package de.lmu.client;

import de.lmu.server.api.Bericht;
import de.lmu.server.api.CallbackIF;

import java.rmi.RemoteException;

public class CallbackEmpfänger implements CallbackIF {

    @Override
    public void zustellen(Bericht bericht) throws RemoteException {
        System.out.println("Bericht empfangen: " + bericht);
    }
}
