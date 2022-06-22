import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {

	private int semmeln = 0;
	private int kapazität;
	private Lock monitor = new ReentrantLock();
	private Condition voll, leer;

	public KitchenCounter(int kapazität) {
		this.kapazität = kapazität;
		monitor = new ReentrantLock();
		voll = monitor.newCondition();
		leer = monitor.newCondition();
	}
	
	public void put() {
		monitor.lock();
		while(semmeln == kapazität) {
			try {
				leer.await();
			} catch (InterruptedException e) { }
		}
		semmeln++;
		System.out.println("put(): noch " + semmeln + " Semmeln auf Theke");
		voll.signal();
		monitor.unlock();
	}
	
	public void take() {
		monitor.lock();
		while(semmeln == 0) {
			try {
				voll.await();
			} catch (InterruptedException e) { }
		}
		semmeln--;
		System.out.println("take(): noch " + semmeln + " Semmeln auf Theke");
		leer.signal();
		monitor.unlock();
	}
	
	public static void main(String[] args) {
		KitchenCounter theke = new KitchenCounter(4);
		new Waiter(theke, "Kellner-1").start();
		new Waiter(theke, "Kellner-2").start();
		for(int i=1; i<=8; i++)
			new Student(theke, "Student-"+i).start();
	
	}

}
