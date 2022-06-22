package Development.uebung02.a;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Student implements Runnable{
    private KitchenCounter othKitchenCounter;
    private String[] nameArray = {"Otto", "David", "Felix", "Sebi", "Alex"};
    private String name;
    Student(KitchenCounter kitchenCounter){
        this.othKitchenCounter = kitchenCounter;
        this.name = nameArray[new Random().nextInt(5)];
    }

    @Override
    public void run(){
        while(true){
            othKitchenCounter.take();
            System.out.println(" " + this.name + " isst!");
            int takeTime = new Random().nextInt(8/2*100); // formula for no bottlenecks
            try{
                TimeUnit.MILLISECONDS.sleep( takeTime );
            } catch (InterruptedException e) {};
        }
    }
}
