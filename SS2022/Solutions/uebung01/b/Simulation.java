package Solutions.uebung01.b;


public class Simulation {

   public static void main(String[] args) throws InterruptedException {
      // neues Parkhaus mit Kapazitaet fuer 10 Autos
      // (aber nur soviele ausfahren duerfen, dass immer 2 drinnen bleiben)
      Parkhaus parkhaus = new Parkhaus(10);
      
      for(int i = 1; i <= 20; i++) {
         new Auto("R-FH " + i, parkhaus).start();
      }
      
      Thread.sleep(60000); 
      System.out.println("Simulation zu Ende.");
   }
}
