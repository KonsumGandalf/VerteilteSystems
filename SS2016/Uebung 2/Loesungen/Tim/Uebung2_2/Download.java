import java.util.Random;
import java.util.concurrent.CountDownLatch;

import javax.swing.JProgressBar;

public class Download extends Thread{
	private CountDownLatch startSignal;
	private CountDownLatch stopSignal;
	private JProgressBar progressBar;
	private Random random = new Random();
	private int value = 0;

	public Download(String name, CountDownLatch startSignal, CountDownLatch stopSignal, 
			JProgressBar progressBar) {
		super(name);
		this.startSignal = startSignal;
		this.stopSignal = stopSignal;
	 	this.progressBar = progressBar;
	}

	@Override
	public void run(){
		try {
			startSignal.await();
            for(int i=0; i<=100; i++){
            	if(value < 100){
            		Thread.sleep((long)(Math.random() * 700));
            		progressBar.setValue(value+=random.nextInt(5)+1);
            	}
            	else
            	{
            		stopSignal.countDown();
            		break;
            	}
    		}
	      } catch (InterruptedException ex){
	    	  ex.printStackTrace();
	      }
	}
}
