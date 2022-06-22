package de.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrüherkennungIF extends Remote {

    public BerichtIF analysieren(Röntgenbild bild) throws RemoteException;
}
