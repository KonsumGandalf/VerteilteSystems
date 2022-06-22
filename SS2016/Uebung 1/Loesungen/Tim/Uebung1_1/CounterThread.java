public class CounterThread extends Thread{
	private int count = 0;
	private String threadName;
	
	public CounterThread(String name){
		this.threadName = name;
	}
	
	@Override 
	public void run(){
		while(count < 100) {
			System.out.println(threadName + ": " + count);
			count++;
		}
	}
}
