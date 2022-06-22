public class Waiter extends Thread{
	private String name;
	private KitchenCounter kCounter;

	public Waiter(KitchenCounter kCounter, String name){
		super(name);
		this.kCounter = kCounter;
	}

	@Override
	public void run(){
		while(true){
			kCounter.put();
			System.out.println(super.getName() + " legt neue Semmel hin");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
