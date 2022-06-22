package Development.uebung01.c;

import java.util.Random;

public class Hersteller implements Runnable{
    private Parkhaus parkhaus;
    Hersteller(Parkhaus parkhaus){
        this.parkhaus = parkhaus;
    }

    @Override
    public void run(){
        while(true){
            int prodZeit = new Random().nextInt(2 * 100);
            try {
                Thread.sleep(prodZeit);
            } catch (InterruptedException e) {e.printStackTrace();}
            parkhaus.abstellen(new Auto());
        }
    }
}
