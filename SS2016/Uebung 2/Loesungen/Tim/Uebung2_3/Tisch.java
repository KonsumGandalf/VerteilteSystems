import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tisch {
	// The number of philosophers
	private static final int NUM_PHILOSOPHERS = 5;
	
	/**
	 * Test the dining philosophers solution
	 * @param args Not used
	 */
	public static void main (String[] args) {
		// Model each chopstick with a lock
		Lock[] chopsticks = new ReentrantLock[NUM_PHILOSOPHERS];
		
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			chopsticks[i] = new ReentrantLock();
		}
		
		// Create the philosophers and start each running in its own thread.
		Philosopher[] philosophers = new Philosopher[NUM_PHILOSOPHERS];
		
		for (int i = 0; i < NUM_PHILOSOPHERS; i++) {
			philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i+1)%NUM_PHILOSOPHERS]);
			new Thread(philosophers[i]).start();
		}
	}

}
