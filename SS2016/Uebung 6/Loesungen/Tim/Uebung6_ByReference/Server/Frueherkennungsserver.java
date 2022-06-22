package SS2016.Uebung;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.util.Random;

public class Frueherkennungsserver implements FrueherkennungsIF{

	public static void main(String[] args) throws RemoteException {
		try{
			FrueherkennungsIF server = new Frueherkennungsserver();
			FrueherkennungsIF stub = (FrueherkennungsIF) UnicastRemoteObject.exportObject(server, 0);
			LocateRegistry.createRegistry(1099);
			Registry verzeichnisdienst = LocateRegistry.getRegistry("localhost", 1099);
			verzeichnisdienst.bind("LMU-Frueherkennungs-Service", stub);
			System.out.println("Server gestartet");
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}

	@Override
	public synchronized BerichtIF analysieren(RoentgenbildIF roentgenBild) throws RemoteException {
		try {
			System.out.println("R�ntgenbild vom " + roentgenBild.getAufnahmeVon() + " von Patient " + roentgenBild.getPatientenName() + " wurde analysiert");
			Thread.sleep(200);
			switch(new Random().nextInt(3)){
				case 0: return new Bericht(new Date(), "Alles okay","Nichts mehr zu tun");
				case 1: return new Bericht(new Date(), "Krebs erkannt","Chemotherapie anordnen");
				case 2: return new Bericht(new Date(), "Gutartiges Geschw�r","Operativ entfernen");
				default: return new Bericht(new Date(), "Alles okay","Nichts mehr zu tun");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
