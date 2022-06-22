package de.lmu.server.api;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface BerichtIF extends Remote {
    Date getDatum() throws RemoteException;
    String getDiagnose() throws RemoteException;
    String getWeiteresVorgehen() throws RemoteException;
}
