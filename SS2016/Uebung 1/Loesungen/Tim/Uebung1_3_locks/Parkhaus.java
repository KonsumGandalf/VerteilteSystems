public class Parkhaus {
	private int parkplaetze;
	private int parkPlaetzeMax;
	
	public Parkhaus(int parkPlaetzeMax){
		this.parkPlaetzeMax = parkPlaetzeMax;
		this.parkplaetze = parkPlaetzeMax;
	}
	
	public synchronized void einfahren(){
		if(parkplaetze == 0){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else{
			notifyAll();
			parkplaetze--;
		}
	}
	
	public synchronized void ausfahren(){
		if(parkplaetze > parkPlaetzeMax){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			notifyAll();
			parkplaetze++;
		}
	}
	
	public synchronized int getParkplaetze(){
		return parkplaetze;
	}
}
