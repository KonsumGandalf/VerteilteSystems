package Development.uebung01.c;

import java.util.Random;

public class Auto{
    private String name;
    private int speed;
    private Random random = new Random();

    Auto() {
        this.name = "RF-H " + random.nextInt(30);
        this.speed = 20 + random.nextInt(180);
    }
    @Override
    public String toString(){
        return "Car: " + this.name;
    }
}
