public class Tisch {
    
    private final boolean gabeln[]; // true = werden gerade genutzt
    private final int anzahl;
    
    public Tisch(int anzahl) {
        this.anzahl = anzahl;
        gabeln = new boolean[anzahl];
        for(int i=0; i<anzahl; i++)
            gabeln[i] = false;
    }
    
    public synchronized void nehmeGabeln(int nummer) {
        int rechts = (nummer == 0)?anzahl-1:nummer-1;
        while( gabeln[nummer] || gabeln[rechts] ) {
            try {
                wait();
            } catch (InterruptedException ex) {
                // ignorieren
            }
        }
        gabeln[nummer] = true;
        gabeln[rechts] = true;
        System.out.println("Philosoph " + nummer + " isst...");
        notifyAll();
    }
    
    public synchronized void legeZurück(int nummer) {
        int rechts = (nummer == 0)?anzahl-1:nummer-1;
        gabeln[nummer] = false;
        gabeln[rechts] = false;
        System.out.println("Philosoph " + nummer + " isst nicht mehr...");
        notifyAll();
    }
    

    public static void main(String[] args) {
        int anzahl = 10;
        Tisch tisch = new Tisch(anzahl);
        
        for(int i=0; i<anzahl; i++)
            new Philosoph(i, tisch).start();
            
    }

}
