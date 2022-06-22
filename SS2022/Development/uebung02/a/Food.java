package Development.uebung02.a;

import java.util.Random;

public class Food {
    private String[] foodSpec = {"Pasta", "Fish", "Burger"};
    private Random rand = new Random();
    private int foodNumber;
    Food(){ // reimplement foodNumber
        this.foodNumber = 2;
    }
    @Override
    public String toString(){
        return "Food "+ this.foodNumber + " | " + this.foodSpec[rand.nextInt(3)];
    }
}
