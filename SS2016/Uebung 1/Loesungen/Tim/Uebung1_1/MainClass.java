public class MainClass {

	public static void main(String[] args) {
		new CounterThread("Counter-1").start();
		new CounterThread("Counter-2").start();
		new CounterThread("Counter-3").start();
		new CounterThread("Counter-4").start();
	}
}
