package de.lmu.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RoentgenbildIF extends Remote {
    Date getAufnameDatum() throws RemoteException;
    String getPatientenName() throws RemoteException;
    byte[] getRawData() throws RemoteException;
}
