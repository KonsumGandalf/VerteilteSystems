package Solutions.uebung01.c;

public class Simulation {

   public static void main(String[] args) throws InterruptedException {

      Parkhaus parkhaus = new Parkhaus(10);

      Hersteller hersteller = new Hersteller(parkhaus);
      hersteller.start();
      
      for(int i = 1; i <= 5; i++) {
         new Kunde(parkhaus).start();
      }

      // Simulation laeuft ewig
   }
}
