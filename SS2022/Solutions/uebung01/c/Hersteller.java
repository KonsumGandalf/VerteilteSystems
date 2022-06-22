package Solutions.uebung01.c;

import java.util.Random;

public class Hersteller extends Thread {
   private static int letztesKennzeichen = 1;
   private Parkhaus parkhaus;
   
   public Hersteller(Parkhaus parkhaus) {
      this.parkhaus = parkhaus;
   }
   
   @Override
   public void run() {
      Random zufallsgenerator = new Random();
      
      while(true) {
         Auto auto = new Auto("R-FH " + letztesKennzeichen++);
         parkhaus.abstellen(auto);
         
         long produktionsZeit = (long)zufallsgenerator.nextInt(200);
         try {
            Thread.sleep(produktionsZeit);
         } catch (InterruptedException ex) {
            /* wird hier ignoriert */
         }
      }
   }
}
