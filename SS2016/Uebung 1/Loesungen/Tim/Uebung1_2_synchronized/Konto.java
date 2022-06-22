public class Konto {
	private long kontostand;

	public synchronized long getKontostand() {
		return kontostand;
	}

	public synchronized void einzahlen(long betrag) {
		this.kontostand += betrag;
	}

	public synchronized void auszahlen(long betrag) {
		this.kontostand -= betrag;
	}

	public synchronized String getKontostandCent(){
		return kontostand + " Cents";
	}
}
