public class Einzahler extends Thread{
	private Konto konto;

	public Einzahler(Konto konto){
		this.konto = konto;
		this.start();
	}

	@Override
	public void run(){
		for(int i = 0; i<100; i++){
			konto.einzahlen(10000); //10000 ct = 100 Euro
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
