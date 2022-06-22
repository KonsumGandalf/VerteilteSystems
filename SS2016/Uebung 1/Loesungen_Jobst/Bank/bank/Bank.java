public class Bank {

	public static void main(String[] args) {
		Konto konto = new Konto();
		new Kontoauszug(konto);
		new Einzahler(konto);
		new Abheber(konto);
	}

}
