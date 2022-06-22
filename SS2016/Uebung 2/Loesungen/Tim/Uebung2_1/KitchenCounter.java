import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class KitchenCounter {
	private int anzahlSemmeln;
	private int anzahlSemmelnMax;
	private Lock lock = new ReentrantLock();
	private Condition put = lock.newCondition();
	private Condition take = lock.newCondition();

	public KitchenCounter(int anzahlSemmelnMax){
		this.anzahlSemmeln = 0;
		this.anzahlSemmelnMax = anzahlSemmelnMax;
	}

	public void put(){
		lock.lock();
		try{
			while(anzahlSemmeln >= anzahlSemmelnMax){
				put.awaitUninterruptibly();
			}
			anzahlSemmeln++;
			System.out.println("Anzahl Leberk�sesemmeln: " + anzahlSemmeln);
			take.signal();
		} finally {
			lock.unlock();
		}
	}

	public void take() {
		lock.lock();
		try{
			while(anzahlSemmeln <= 0){
				take.awaitUninterruptibly();
			}
			anzahlSemmeln--;
			System.out.println("Anzahl Leberk�sesemmeln: " + anzahlSemmeln);
			put.signal();
		} finally {
			lock.unlock();
		}
	}
}
