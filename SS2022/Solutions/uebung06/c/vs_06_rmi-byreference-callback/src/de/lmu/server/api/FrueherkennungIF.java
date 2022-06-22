package de.lmu.server.api;

import de.lmu.server.api.Bericht;
import de.lmu.server.api.Roentgenbild;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    void analysiere(Roentgenbild roentgenbild, CallbackIF callbackStub) throws RemoteException;
}
