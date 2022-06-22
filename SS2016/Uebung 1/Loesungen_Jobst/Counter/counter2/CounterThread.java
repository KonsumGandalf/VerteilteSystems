public class CounterThread implements Runnable {
	
	private Tafel tafel;
	private String name;
	
	public CounterThread(String name, Tafel tafel) {
		this.name = name;
		this.tafel = tafel;
		Thread t = new Thread(this);
		t.setName(name);
		t.start();
	}

	@Override
	public void run() {
		int i;
		while ( (i=tafel.getCounter()) < 100 ) {
			i = tafel.increment(this.name);
		}
	}
}
