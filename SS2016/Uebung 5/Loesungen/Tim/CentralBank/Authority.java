package SS2016.Uebung;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Authority implements IAuthority {

	public static void main(String[] args) {
		IAuthority zentralbank = new Authority();
		try {
			IAuthority stub = (IAuthority) UnicastRemoteObject.exportObject(zentralbank, 0);
			Registry r = LocateRegistry.getRegistry(1099);
			r.rebind("Authority", stub);
			System.out.println("Authority (Zentralbank) gestartet");
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void requestApproval(Cheque scheck) throws RemoteException {
		System.out.println("Request approval für Scheck " + scheck);		
	}

	@Override
	public boolean obtainApproval() throws RemoteException {
		boolean entscheidung = Math.random() < 0.7; // 70 % der Schecks werden approved
		return entscheidung;
	}

}
