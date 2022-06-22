package Development.uebung01.c;

public class Simulation {
    public static void main(String[] args) throws InterruptedException{
        Parkhaus pk = new Parkhaus(10);

        Thread p = new Thread(new Hersteller(pk));
        p.setDaemon(true);
        p.start();

        for(int i=0;i <= 6; i++){
            Thread t;
            if(i == -1){
                t = new Thread(new Hersteller(pk));
            } else {
                t = new Thread(new Kunde(pk));
            }
            t.setDaemon(true);
            t.start();
        }

        Thread.sleep(20000);
        System.out.println("Alle schlafen jetzt... Die Werwölfe erwachen!");
    }
}
