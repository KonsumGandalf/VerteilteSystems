package de.lmu.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CallbackIF extends Remote {
    void zustellen(Bericht bericht) throws RemoteException;
}
