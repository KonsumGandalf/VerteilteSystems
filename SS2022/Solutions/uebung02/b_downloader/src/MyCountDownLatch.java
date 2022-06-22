package Solutions.uebung02.b_downloader.src;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyCountDownLatch {

    private long counter;
    private Timer timer = new Timer();

    MyCountDownLatch(int counter){
        this.counter = counter;
    }

    public synchronized void countDown(){
        if (this.counter > 0) {

            this.counter -= 1;

            if (this.counter == 0){
                this.notifyAll();
                this.timer.cancel();
            }
        }
    }

    public synchronized long getCounter(){
        return this.counter;
    }

    public synchronized void await() throws InterruptedException {
        while(this.counter != 0) {
            this.wait();
        }
    }

    public synchronized boolean await(long timeout, TimeUnit unit) throws InterruptedException{
        long millisToWait = unit.toMillis(timeout);
        long timeoutMillis = System.currentTimeMillis() + millisToWait;

        TimerTask taskForTimeout = new TimerTask(){
            @Override
            public void run(){
                synchronized (MyCountDownLatch.this){ // Referenz auf das "this" der "äußeren" Klasse (dieser anonymen inneren Klasse)
                    MyCountDownLatch.this.notifyAll();
                }
            }
        };

        timer.schedule(taskForTimeout, millisToWait);

        synchronized (this){
            while (System.currentTimeMillis() < timeoutMillis && this.counter != 0){
                this.wait();
            }
        }

        synchronized (this){
            return this.counter == 0;
        }
    }

    @Override
    public synchronized String toString() {
        return "MyCountDownLatch{" +
                "counter=" + counter +
                '}';
    }

}
