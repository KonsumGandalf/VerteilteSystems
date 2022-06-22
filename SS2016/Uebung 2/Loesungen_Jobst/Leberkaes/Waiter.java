import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Waiter extends Thread {

	private KitchenCounter theke;
	
	public Waiter(KitchenCounter theke, String name) {
		super(name);
		this.theke = theke;
	}
	
	@Override
	public void run() {
		while(true) {
			theke.put();
			System.out.println("  " + Thread.currentThread().getName() + " neue Semmel abgelegt!");
			try {
				TimeUnit.SECONDS.sleep( new Random().nextInt(2) );
			} catch (InterruptedException e) {
			}
		}
	}

}
