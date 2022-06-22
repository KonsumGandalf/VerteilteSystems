package SS2016.Uebung;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

	public static void main(String[] args) {
		IBanking konto = new Account();
		try {
			IBanking kontoRemote = (IBanking) UnicastRemoteObject.exportObject(konto, 0);
			LocateRegistry.createRegistry(1099);
			Registry r = LocateRegistry.getRegistry("localhost", 1099);
			r.rebind("Konto", kontoRemote);
			System.out.println("Server gestartet!");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
