package SS2016.Uebung;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Server.FrueherkennungsIF;
import Server.RoentgenbildIF;
import Server.BerichtIF;

public class Client implements FrueherkennungsIF{

	private String name;

	public Client(){
	}

	public Client(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public BerichtIF analysieren(RoentgenbildIF roentgenBild) throws RemoteException {
		try {
			Registry r = LocateRegistry.getRegistry("localhost", 1099);
			FrueherkennungsIF frueherkennung = (FrueherkennungsIF) r.lookup("LMU-Frueherkennungs-Service");
			return frueherkennung.analysieren(roentgenBild);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
