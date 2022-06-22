public class Kontoauszug extends Thread{
	private Konto konto;

	public Kontoauszug(Konto konto){
		this.konto = konto;
		this.start();
	}

	@Override
	public void run(){
		for(int i = 1; i<=15; i++){
			System.out.println("Kontostand zum Zeitpunkt "+ i
					+ ": " + konto.getKontostandCent());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
