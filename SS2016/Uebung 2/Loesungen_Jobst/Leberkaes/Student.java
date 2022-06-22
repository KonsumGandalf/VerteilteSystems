import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Student extends Thread {

	private KitchenCounter theke;
	
	public Student(KitchenCounter theke, String name) {
		super(name);
		this.theke = theke;
	}

	@Override
	public void run() {
		while(true) {
			theke.take();
			System.out.println("  " + Thread.currentThread().getName() + " isst!");
			try {
				TimeUnit.SECONDS.sleep( new Random().nextInt(10) );
			} catch (InterruptedException e) {
			}
		}
		
	}

}
