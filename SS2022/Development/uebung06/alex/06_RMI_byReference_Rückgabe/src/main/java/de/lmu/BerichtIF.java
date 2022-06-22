package de.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface BerichtIF extends Remote {

    Date getDate() throws RemoteException;

    void setDiagnose(String diagnose) throws RemoteException;

    String getDiagnose() throws RemoteException;
}
