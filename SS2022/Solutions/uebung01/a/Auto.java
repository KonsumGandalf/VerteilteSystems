package Solutions.uebung01.a;

import java.util.Random;

public class Auto extends Thread {
   
   private Parkhaus parkhaus;

   public Auto(String kennzeichen, Parkhaus parkhaus) {
      super(kennzeichen); // "Weitergabe" an Konstruktor der Klasse Thread
      this.parkhaus = parkhaus;
      setDaemon(true);
   }
   
   @Override
   public void run() {
      Random zufallsgenerator = new Random();
      
      while(true) {
         // Fahren
         int fahrzeit = zufallsgenerator.nextInt(10);
         try {
            Thread.sleep(fahrzeit * 1000L);
         } catch (InterruptedException ex) {
            System.out.println("Huch, wurde aufgeweckt. Beende mich.");
            return;
         }
         
         // Parken
         parkhaus.einfahren();
         int parkzeit = zufallsgenerator.nextInt(10);
         try {
            Thread.sleep(parkzeit * 1000L);
         } catch (InterruptedException ex) {
            System.out.println("Huch, wurde aufgeweckt. Beende mich.");
            return;
         }
         parkhaus.ausfahren();
      }
   }
   
}
