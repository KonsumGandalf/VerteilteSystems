package de.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RoentgenbildIF extends Remote {
    public void print_Roentgenbild() throws RemoteException;
    public Date getAufnahmeVom() throws RemoteException;
    public void setAufnahmeVom(Date aufnahmeVom) throws RemoteException;
    public String getPatientenName() throws RemoteException;
    public void setPatientenName(String patientenName) throws RemoteException;
    public byte[] getRawData() throws RemoteException;
    public void setRawData(byte[] rawData) throws RemoteException;
}
