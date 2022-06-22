package Development.uebung01.a.c_schneckenrennen;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Schneckenrennen {
    public static void main(String[] args) throws InterruptedException {
        CyclicBarrier startSync = new CyclicBarrier(4);
        CountDownLatch wellnessSync = new CountDownLatch(2);

        new Schnecke(1, startSync, wellnessSync).start();
        new Schnecke(2, startSync, wellnessSync).start();
        new Schnecke(3, startSync, wellnessSync).start();
        Thread.sleep(1000); // die 4. Schnecke braucht immer am laengsten
        new Schnecke(4, startSync, wellnessSync).start();
        
        
    }
}

class Schnecke extends Thread {
    private int meineNr;
    private CyclicBarrier start;
    private CountDownLatch wellness;
    
    public Schnecke(int nr, CyclicBarrier start, CountDownLatch wellness){
        this.meineNr = nr; this.start = start; this.wellness = wellness;
    }
    @Override public void run() {
        
        try {
            System.out.println("Schnecke " + meineNr + " auf Startposition");
            start.await(); // Blockierendes Warten auf "Startschuss"
            System.out.println("Schnecke " + meineNr + " rennt los!");
            Thread.sleep((long) new Random().nextInt(10000));
            System.out.println("Schnecke " + meineNr + " am Ziel");
            
            wellness.countDown();
            wellness.await(); // Blockierendes Warten, bis zwei Threds den countDown runtergezaehlt haben
            System.out.println("Schnecke " + meineNr + " relaxt jetzt. Good Bye.");
            
        } catch (InterruptedException | BrokenBarrierException ex) {
            // Fehler behandeln
        }
    }
}
