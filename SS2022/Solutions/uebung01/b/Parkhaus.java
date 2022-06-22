package Solutions.uebung01.b;


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
               System.out.println("  Warten an Schranke (zur Einfahrt): " + Thread.currentThread().getName());
               schranke.wait();
            } catch (InterruptedException ex) {
               /* Fehlerbehandlung: hier einfach "weiter schlafen" */
            }
         }
         System.out.println("Einfahrt: " + Thread.currentThread().getName());
         this.belegt++;
         schranke.notifyAll(); 
         // Ein "drinnen" wartendes Auto koennte jetzt ausfahren
         // Da am Monitor aber auch Autos warten, die "rein" wollen, muessen alle benachrichtigt werden
      }
   }
      
   public void ausfahren() {
      synchronized(schranke) {
         while(belegt <= 2) {
            try {
               System.out.println("  Warten an Schranke (zur Ausfahrt): " + Thread.currentThread().getName());
               schranke.wait();
            } catch (InterruptedException ex) {}
         }
         this.belegt--;
         System.out.println("Ausfahrt: " + Thread.currentThread().getName());
         schranke.notifyAll(); 
         // Ein draussen wartendes Auto benachrichtigen
         // (aber hier wieder alle, da ja alle Auto-Threads (drinnen wie draussen)
         //  am selben Monitor warten)
      }
   }
}
