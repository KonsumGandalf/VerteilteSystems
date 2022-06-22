public class Kontoauszug extends Thread {
	
	private Konto konto;
	
	public Kontoauszug(Konto konto) {
		this.konto = konto;
		start();
	}
	
	@Override
	public void run() {
		int i=0;
		while(i++ < 15) {
			int kontostand = konto.getKontostand();
			System.out.printf("Kontostand zum Zeitpunkt %2d: %8d Cents.\n", i, kontostand);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) { /* wird ignoriert */ }
		}
	}
}
