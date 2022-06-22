public class Abheber implements Runnable {

	private Konto konto;
	
	public Abheber(Konto konto) {
		this.konto = konto;
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		for(int i=0; i<100; i++) {
			konto.auszahlen(10000);
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) { /* wird ignoriert */ }
		}
	}

}
