package Solutions.uebung01.c;

import java.util.Deque;
import java.util.LinkedList;

// passive Klasse
public class Parkhaus {
   private int kapazitaet;
   private final Object schranke = new Object();
   // Deque = double ended queue (mit addFirst/addLast, pollFirst/pollLast)
   private Deque<Auto> stellplaetze = new LinkedList<>();
   
   public Parkhaus(int kapazitaet) {
      this.kapazitaet = kapazitaet;
   }
   
   public void abstellen(Auto auto) {
      synchronized(schranke) {
         while(stellplaetze.size() == kapazitaet) {
            try {
               System.out.println("Kapazitaetsgrenze erreicht!");
               schranke.wait();
            } catch (InterruptedException ex) {
               /* Fehlerbehandlung: hier einfach "weiter schlafen" */
            }
         }
         System.out.println("Abgestellt: " + auto.toString());
         stellplaetze.addLast(auto);
         schranke.notifyAll();
      }
   }

   public Auto kaufen() {
      synchronized(schranke) {
         while(stellplaetze.size() == 0) {
            try {
               System.out.println("Derzeit kein Auto verfuegbar!");
               schranke.wait();
            } catch (InterruptedException ex) {}
         }
         Auto auto = stellplaetze.pollFirst();
         System.out.println("Verkaufe: " + auto.toString());
         try {
            return auto;
         } finally {
            schranke.notifyAll();
         }
         // einfacher und ebenfalls okay:
         // schranke.notifyAll();
         // return auto;
      }
   }
}
