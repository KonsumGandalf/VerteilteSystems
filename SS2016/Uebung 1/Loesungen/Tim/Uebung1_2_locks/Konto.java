import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Konto {
	private long kontostand;
	private Lock lock = new ReentrantLock();
	private Condition einzahlen = lock.newCondition();
	private Condition auszahlen = lock.newCondition();

	public long getKontostand() {
		return kontostand;
	}

	public void einzahlen(long betrag) {
		lock.lock();
		try{
			while(kontostand <= 0){
				einzahlen.awaitUninterruptibly();
			}
			this.kontostand += betrag;
			auszahlen.signal();
		} finally {
			lock.unlock();
		}
	}

	public void auszahlen(long betrag) {
		lock.lock();
		try{
			while(kontostand >= 0){
				auszahlen.awaitUninterruptibly();
			}
			this.kontostand -= betrag;
			einzahlen.signal();
		} finally {
			lock.unlock();
		}
	}

	public String getKontostandCent(){
		return kontostand + " Cents";
	}
}
