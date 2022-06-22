package Solutions.uebung01.c;

public class Auto {
   
   private String kennzeichen;

   public Auto(String kennzeichen) {
      this.kennzeichen = kennzeichen;
   }

   @Override
   public String toString() {
      return "Auto{" + "kennzeichen=" + kennzeichen + '}';
   }
   
}
