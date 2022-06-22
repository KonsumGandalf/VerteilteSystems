public class Konto {
	private long kontostand;

	public long getKontostand() {
		return kontostand;
	}

	public void einzahlen(long betrag) {
		this.kontostand += betrag;
	}

	public void auszahlen(long betrag) {
		this.kontostand -= betrag;
	}

	public String getKontostandCent(){
		return kontostand + " Cents";
	}
}
