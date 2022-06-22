package Development.uebung02.c_schneckenrennen;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

public class Schnecke implements Runnable{
    private String name;
    private int speed, distance, advance;
    private final Random random = new Random();
    private CountDownLatch stop;
    private CyclicBarrier start;
    Schnecke(CyclicBarrier start, CountDownLatch stop, String name, int distance){
        this.start = start;
        this.stop = stop;
        this.name = name;
        this.speed = random.nextInt(10)+10;
        this.advance = 0;
        this.distance = distance;
    }

    @Override
    public void run(){
        try{
            start.await();
            System.out.println("Schecke " + this.name + " has begun the race");
            while(advance < distance) {
                advance += this.speed;
                System.out.println("Schecke " + this.name + " : " + advance + " / " + distance);
                TimeUnit.MILLISECONDS.sleep(100);
            }
            System.out.println("Schecke " + this.name + " finished");
            stop.countDown();
            stop.await();
        } catch (Exception e) {
        }
    }
}
