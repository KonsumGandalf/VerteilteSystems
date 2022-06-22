import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parkhaus {
	private int parkplaetze;
	private int parkPlaetzeMax;
	private Lock lock = new ReentrantLock();
	private Condition einfahren = lock.newCondition();
	private Condition ausfahren = lock.newCondition();
	
	public Parkhaus(int parkPlaetzeMax){
		this.parkPlaetzeMax = parkPlaetzeMax;
		this.parkplaetze = parkPlaetzeMax;
	}
	
	public void einfahren(){
		lock.lock();
		try{
			while(parkplaetze == 0){
				einfahren.awaitUninterruptibly();
			}
			parkplaetze--;
			ausfahren.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public void ausfahren(){
		lock.lock();
		try{
			while(parkplaetze > parkPlaetzeMax){
				ausfahren.awaitUninterruptibly();
			}
			parkplaetze++;
			einfahren.signal();
		} finally {
			lock.unlock();
		}
	}
	
	public int getParkplaetze(){
		return parkplaetze;
	}
}
