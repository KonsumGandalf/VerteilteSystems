package de.lmu;

import de.client.CallbackIF;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

    public void analysieren(Roentgenbild rb, CallbackIF cb) throws RemoteException;
}
