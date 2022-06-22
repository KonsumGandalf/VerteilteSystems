public class Auto extends Thread {
	
	private Parkhaus parkhaus;
	
	public Auto(String name, Parkhaus parkhaus) {
		super(name);
		this.parkhaus = parkhaus;
		start();
	}
	
	@Override
	public void run() {
		while(true) {
			
			// FAHREN
			System.out.println(super.getName() + ": brum, brum, ...");
			try {
				sleep((long) (Math.random()*10000));
			} catch (InterruptedException e) { 
				/* ignorieren */ 
			}

			// PARKEN
			parkhaus.einfahren();
			System.out.println(super.getName() + ": >> rein >>");
			try {
				sleep((long) (Math.random()*10000));
			} catch (InterruptedException e) { 
				/* ignorieren */ 
			}
			parkhaus.ausfahren();
			System.out.println(super.getName() + ": << raus <<");
		}
		
	}
}
