package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RoentgenbildIF extends Remote{

	 Date getAufnahmeVon() throws RemoteException;

	 String getPatientenName()throws RemoteException;

	 byte[] getRawData()throws RemoteException;
}
