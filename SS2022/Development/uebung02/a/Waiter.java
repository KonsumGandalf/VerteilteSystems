package Development.uebung02.a;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Waiter implements Runnable{
    private KitchenCounter othKitchenCounter;
    private String[] nameArray = {"Norbet", "Magaret", "Sibile"};
    private String name;
    Waiter(KitchenCounter kitchenCounter){
        this.othKitchenCounter = kitchenCounter;
        this.name = nameArray[new Random().nextInt(3)];
    }

    @Override
    public void run(){
        while(true){
            othKitchenCounter.put();

            int serveTime = new Random().nextInt(100); // formula for no bottlenecks
            try{
                TimeUnit.MILLISECONDS.sleep(serveTime);
            } catch (InterruptedException e) {};
        }
    }
}
