import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Konto {
	private int kontostand = 0;
	private Lock lock;
	
	public Konto() {
		lock = new ReentrantLock();
	}
	
	public  void einzahlen(int betrag) {
		lock.lock();
		kontostand += betrag;
		lock.unlock();
	}
	
	public  void auszahlen(int betrag) {
		lock.lock();
		kontostand -= betrag;
		lock.unlock();
	}
	
	public  int getKontostand() {
		lock.lock();
		try {
			return kontostand;
		}
		finally {
			lock.unlock();
		}
	}
}
