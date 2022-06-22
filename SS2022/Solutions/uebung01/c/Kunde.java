package Solutions.uebung01.c;

import java.util.Random;

public class Kunde extends Thread {
   private Parkhaus parkhaus;
   
   public Kunde(Parkhaus parkhaus) {
      this.parkhaus = parkhaus;
   }
   
   @Override
   public void run() {
      Random zufallsgenerator = new Random();
      
      while(true) {
         Auto auto = parkhaus.kaufen();
         
         long warteZeit = (long)zufallsgenerator.nextInt(1000);
         try {
            Thread.sleep(warteZeit);
         } catch (InterruptedException ex) {
            /* wird hier ignoriert */
         }
      }
   }
}
