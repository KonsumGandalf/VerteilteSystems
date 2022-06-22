import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CounterThread extends Thread {
	/* 2. Implementierungs-Möglichkeit mit Runnable --> siehe Aufgabe 2 */

	public int counter = 0;
	
	public CounterThread(String name) {
		super(name);
		start();
	}
	
	@Override
	public void run() {
		while(counter < 100) {
			System.out.printf("%s \t %3d \n", Thread.currentThread().getName(),  ++counter);
		}
	}
	
	public static void main(String[] args) {
		// Start von vier Threads
		ReentrantLock l = new ReentrantLock();
		System.out.println(l.isHeldByCurrentThread());
		
		l.lock();
		System.out.println(l.isHeldByCurrentThread());
		
		
		new CounterThread("Counter-1");
		new CounterThread("Counter-2");
		new CounterThread("Counter-3");
		new CounterThread("Counter-4");
	}
	

}
