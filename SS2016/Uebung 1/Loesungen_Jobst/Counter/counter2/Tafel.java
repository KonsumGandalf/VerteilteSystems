public class Tafel {
	
	private int counter = 0;
	
	public Tafel() {}
	
	public synchronized int increment(String aufruferName) {
		counter++;
		System.out.println(counter + " war " + aufruferName);
		return counter;
	}
	
	public synchronized int getCounter() {
		return this.counter;
	}
	
	public static void main(String[] args) {
		Tafel gemeinsameTafel = new Tafel();
		// Start von vier Threads
		new CounterThread("Yosemite", gemeinsameTafel);
		new CounterThread("Mavericks", gemeinsameTafel);
		new CounterThread("Mountain Lion", gemeinsameTafel);
		new CounterThread("Snow Leopard", gemeinsameTafel);
		
	}
}
