package SS2016.Uebung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import de.oth.vs.bank.server.Cheque;
import de.oth.vs.bank.server.IBanking;

public class TestClient {

	public static void main(String[] args) {
		try {
			Registry r = LocateRegistry.getRegistry("localhost", 1099);
			IBanking konto = (IBanking) r.lookup("Konto");
			System.out.println("Testclient: 100 Euro einzahlen");
			konto.deposit(10000);
			System.out.println("Testclient: nochmal 100 Euro einzahlen");
			konto.deposit(10000);
			System.out.println("Testclient: 50 Euro abheben");
			konto.withdraw(5000);
			System.out.println("Testclient: Scheck mit 200 Euro einreichen");
			konto.deposit(new Cheque(2000, "1234", "HVB"));
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
