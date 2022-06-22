public class Scenario {

	public static void main(String[] args) {
		KitchenCounter theke = new KitchenCounter(4);
		new Waiter(theke, "Kellner---1").start();
		new Waiter(theke, "Kellner---2").start();
		for(int i=1; i<=8; i++){
			new Student(theke, "Student---"+i).start();
		}
	}

}
