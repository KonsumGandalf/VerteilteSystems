package Development.uebung01.a;

import java.util.Random;

public class Auto implements Runnable{
    private String name;
    private int speed;
    private Parkhaus parkhaus;
    private Random random = new Random();
    Auto(Parkhaus parkhaus){
        this.name = "RF-H " + random.nextInt(30);
        this.speed = 20 + random.nextInt(180);
        this.parkhaus = parkhaus;
    }

    @Override
    public String toString(){
        return "Car: " + this.name;
    }

    public void run(){
        while(true){
            int fahrzeit = random.nextInt(10);
            try {
                Thread.sleep(1000*fahrzeit);
            } catch (InterruptedException e){
                System.out.println("Hello there");
                return;
            }

            this.parkhaus.einfahren(this);

            int parkzeit = random.nextInt(10);
            try {
                Thread.sleep(parkzeit * 1000L);
            } catch (InterruptedException ex) {
                System.out.println("Huch, wurde aufgeweckt. Beende mich.");
                return;
            }
            this.parkhaus.ausfahren(this);
        }
    }
}
