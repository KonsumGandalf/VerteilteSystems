package SS2016.Uebung;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Account implements IBanking {

	private int kontostand = 0;
	
	@Override
	public synchronized void withdraw(int cents) {
		this.kontostand -= cents;
		System.out.printf("Neuer Kontostand: %.2f EUR%n", (double)kontostand/100);
	}

	@Override
	public synchronized void deposit(int cents) {
		this.kontostand += cents;
		System.out.printf("Neuer Kontostand: %.2f EUR%n", (double)kontostand/100);
	}

	@Override
	public void deposit(Cheque scheck) throws RemoteException {
		this.deposit( scheck.getBetrag() );
		System.out.printf("Scheckgutschrift E.V.!! Neuer Kontostand: %.2f EUR%n", (double)kontostand/100);
		try {
			Registry r = LocateRegistry.getRegistry(1099);
			IAuthority zentralbank = (IAuthority) r.lookup("Authority");
			zentralbank.requestApproval(scheck);
		} catch (RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					Registry r = LocateRegistry.getRegistry(1099);
					IAuthority zentralbank = (IAuthority) r.lookup("Authority");
					// könnte ggf. länger dauern, deshalb in eigenem Runnable
					if(!zentralbank.obtainApproval()) {
						System.out.println("Scheck ist geplatzt! Betrag wird wieder abgezogen!");
						Account.this.withdraw( scheck.getBetrag() );
					} else {
						System.out.println("Scheck ist in Ordnung!");
					}
				} catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
