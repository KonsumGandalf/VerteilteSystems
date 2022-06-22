package Development.uebung01.a;

public class Parkhaus {
    private int capacity;
    private int currentCars = 0;
    Parkhaus(int capacity){
        this.capacity = capacity;
    }

    public synchronized void einfahren(Auto curCar){
        while (this.currentCars >= this.capacity){
            try {
                System.out.println("  Warten an Schranke: " + curCar);
                wait();
            } catch (InterruptedException e) { System.out.println("coundnt wait");e.printStackTrace();}
        }
        System.out.println("Einfahrt: " + curCar);
        this.currentCars++;
    }

    public synchronized void ausfahren(Auto curCar){
        while (this.currentCars <= 2){
            try {
                System.out.println("  Warten auf Ausfahrt: " + curCar);
                wait();
            } catch (InterruptedException e) { System.out.println("coundnt wait");e.printStackTrace();}
        }
        System.out.println("Ausfahrt: " + curCar);
        this.currentCars--;
        this.notify();
    }
}
