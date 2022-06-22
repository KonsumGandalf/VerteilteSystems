public class Student extends Thread{
	private String name;
	private KitchenCounter kCounter;

	public Student(KitchenCounter kCounter, String name){
		super(name);
		this.kCounter = kCounter;
	}

	@Override
	public void run(){
		while(true){
			kCounter.take();
			System.out.println(super.getName() + " isst Semmel");
			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
