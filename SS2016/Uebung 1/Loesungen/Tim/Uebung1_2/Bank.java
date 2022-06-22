public class Bank {

	public static void main(String[] args) {
		Konto konto = new Konto();
		Einzahler einzahler = new Einzahler(konto);
		Auszahler auszahler = new Auszahler(konto);
		Kontoauszug kontoauszug = new Kontoauszug(konto);
	}
}