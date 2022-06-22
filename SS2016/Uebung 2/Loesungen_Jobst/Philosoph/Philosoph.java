public class Philosoph extends Thread {
    
    private int meineNummer;
    private Tisch tisch;
    
    public Philosoph(int nummer, Tisch tisch) {
        this.meineNummer = nummer;
        this.tisch = tisch;
    }
    
    public void run() {
        int i=0;
        while(i++ < 1) {
            denken();
            essen();
        }
    }
    
    public void denken() {
        try {
            Thread.sleep((long)(Math.random() * 10000));
        } catch (InterruptedException ex) {
            // ignorieren
        }
    }
    
    public void essen() {
        tisch.nehmeGabeln(meineNummer);
        try {
            Thread.sleep((long)(Math.random() * 10000));
        } catch (InterruptedException ex) {
            // ignorieren
        }
        tisch.legeZurück(meineNummer);
    }
}
