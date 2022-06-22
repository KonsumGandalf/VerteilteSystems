package Development.uebung01.c;

import java.util.Random;

public class Kunde implements Runnable{

    private Parkhaus parkhaus;
    Kunde(Parkhaus parkhaus){
        this.parkhaus = parkhaus;
    }

    public void run(){
        while(true) {
            int ausfahrZeit = new Random().nextInt(1 * 1000);
            try {
                Thread.sleep(ausfahrZeit);
            } catch (Exception e) {
                e.printStackTrace();
            }
            parkhaus.abholen();
        }
    }
}
