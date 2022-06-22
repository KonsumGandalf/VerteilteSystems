package Solutions.uebung01.a;

// Passive Klasse


public class Parkhaus {
   private int kapazitaet = 10;
   private int belegt = 0;
   private final Object schranke = new Object();
   
   public Parkhaus(int kapazitaet) {
      this.kapazitaet = kapazitaet;
   }
   
   public void einfahren() {
      synchronized(schranke) {
         while(belegt >= kapazitaet) {
            try {
               System.out.println("  Warten an Schranke: " + Thread.currentThread().getName());
               schranke.wait();
            } catch (InterruptedException ex) {
               /* Fehlerbehandlung: hier einfach "weiter schlafen" */
            }
         }
         System.out.println("Einfahrt: " + Thread.currentThread().getName());
         this.belegt++;
         //schranke.notify(); 
         // hier ist KEIN notify() noetig,
         // da alle "schlafenden Threads" (= wartende Autos vor belegtem Parkhaus)
         // nicht geweckt werden müssen, wenn jemand einfaehrt
      }
   }
      
   public void ausfahren() {
      synchronized(schranke) {
         // eigentlich muesste es hier heissen:
         /* while(belegt <= 0) {
            try {
               schranke.wait();
            } catch (InterruptedException ex) {}
         */
         // das ist in diesem Fall aber nicht noetig, sofern die Implementierung in den
         // Autos sicherstellt, dass immer erst ausgefahren wird, nachdem auch reingefahren wurde
         this.belegt--;
         System.out.println("Ausfahrt: " + Thread.currentThread().getName());
            schranke.notify(); 
         // Ausfahrende Autos muessen aber unbedingt andere wartende Autos benachrichtigen
         // In diesem Fall nur jeweils eines: notify() 
         // (denn ein Auto macht ja schliesslich nur Platz fuer ein neues Auto)
         
         // Neue Erkenntnis:
         // das notify() hier korrespondiert irgendwie fachlich mit dem wait() aus der "Gegenmethode"
      }
   }
}
