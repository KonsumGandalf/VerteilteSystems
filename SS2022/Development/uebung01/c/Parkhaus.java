package Development.uebung01.c;

import java.util.Deque;
import java.util.LinkedList;

public class Parkhaus {
    private int capacity;
    private final Object schranke = new Object();

    private Deque<Auto> parkedCars = new LinkedList<>();
    Parkhaus(int capacity){
        this.capacity = capacity;
    }

    public void abstellen(Auto auto) {
        synchronized(schranke) {
            while (parkedCars.size() == this.capacity) {
                try {
                    System.out.println("Kapazitaetsgrenze erreicht!");
                    schranke.wait();
                } catch (InterruptedException ex) {
                    /* Fehlerbehandlung: hier einfach "weiter schlafen" */
                }
            }
            System.out.println("Abgestellt: " + auto.toString());
            parkedCars.addLast(auto);
            schranke.notifyAll();
        }

    }

    public Auto abholen() {
        synchronized(schranke) {
            while (parkedCars.size() == 0) {
                try {
                    System.out.println("Derzeit kein Auto verfuegbar!");
                    schranke.wait();
                } catch (InterruptedException ex) {
                }
            }
            Auto auto = parkedCars.pollFirst();
            System.out.println("Verkaufe: " + auto.toString());
            try {
                return auto;
            } finally {
                schranke.notifyAll();
            }
            // einfacher und ebenfalls okay:
            // this.notifyAll();
            //
        }
    }
}
