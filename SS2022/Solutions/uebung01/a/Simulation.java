package Solutions.uebung01.a;

public class Simulation {

   public static void main(String[] args) throws InterruptedException {
      // neues Parkhaus mit Kapazitaet fuer 10 Autos
      Parkhaus parkhaus = new Parkhaus(10);
      
      for(int i = 1; i <= 20; i++) {
         new Auto("R-FH " + i, parkhaus).start();
      }
      
      Thread.sleep(60000); 
      System.out.println("Simulation zu Ende.");
   }
}
