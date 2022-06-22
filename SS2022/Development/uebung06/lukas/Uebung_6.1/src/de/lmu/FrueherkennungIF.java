package de.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    public Bericht analysieren(RoentgenbildIF rb) throws RemoteException;
}
