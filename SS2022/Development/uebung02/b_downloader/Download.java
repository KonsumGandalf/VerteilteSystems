package Development.uebung02.b_downloader;


import javax.swing.JProgressBar;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

// aktive Klasse
public class Download implements Runnable
{
	
	private final JProgressBar balken;
    // weitere Attribute zur Synchronisation hier definieren
    private int balkenanzahl;
	private Browser browser;
	private int balkenMax;
	private MyCountDownLatch start, end;
	private Random rand = new Random();
	public Download(JProgressBar balken, MyCountDownLatch start, MyCountDownLatch end) {
		this.balken = balken;
		this.balkenMax = balken.getMaximum();
		this.start = start;
		this.end = end;
		new Thread(this).start();
    }

	@Override
	public void run(){
		int addedStrip = 0;
		try{
			this.start.await();
			while(this.balken.getValue() != this.balkenMax){
				TimeUnit.MILLISECONDS.sleep(rand.nextInt(5)*100);
				addedStrip += rand.nextInt((int) (balkenMax*0.05));
				this.balken.setValue(addedStrip);
				System.out.println(balken.getValue() + " / " + this.balkenMax);
			}
			end.countDown();
		} catch (InterruptedException e) { e.printStackTrace(); }
		finally { }
	}

    /*  hier die Methode definieren, die jeweils den Balken weiterschiebt
     *  Mit balken.getMaximum() bekommt man den Wert fuer 100 % gefuellt
     *  Mit balken.setValue(...) kann man den Balken einstellen (wieviel gefuellt) dargestellt wird
     *  Setzen Sie den value jeweils und legen Sie die Methode dann für eine zufaellige Zeit schlafen
     */


}
