package Development.uebung02.c_schneckenrennen;

import java.util.concurrent.*;

public class Schneckenrennen {

    private final static int numberOfSchnecken = 4;
    private final static int distance = 100;
    private final static String[] nameArray = {"David", "Felix", "Sebastian", "Lennard"};
    public static void main(String[] args){
        System.out.println("All schnecken are up and waited for.");
        CyclicBarrier aufwaermenSync = new CyclicBarrier(numberOfSchnecken);
        CountDownLatch laufSync = new CountDownLatch(numberOfSchnecken/2);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < numberOfSchnecken; i++){
            executor.submit(new Schnecke(aufwaermenSync, laufSync, nameArray[i], distance));
        }
        try {
            laufSync.await();
            System.out.println("First schnecken have ended the race.");
        } catch (InterruptedException e) {}
    }
}
