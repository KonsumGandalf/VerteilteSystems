public class Parkhaus {
	
	private int kapazität;
	private int autos = 0;
	
	public Parkhaus(int kapazität) {
		this.kapazität = kapazität;
	}
	
	public synchronized void einfahren() {
		while(autos == kapazität) {
			try {
				wait();
			} catch (InterruptedException e) {
				/* absichtlich ignorieren, 
				 * wenn was passiert (d. h. Warten wird abgebrochen)
				 * muss trotzdem erst die fachliche Bedingung oben
				 * geprüft werden. Ggf. wurde fälschlicherweise aufgeweckt.
				 */
			}
		}
		autos++;
		System.out.println("\tim Parkhaus aktuell: " + autos);
		notify();
	}
	
	public synchronized void ausfahren() {
		autos--;
		System.out.println("\tim Parkhaus aktuell: " + autos);
		notify();
	}
}
