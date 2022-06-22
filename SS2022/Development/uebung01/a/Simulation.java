package Development.uebung01.a;

public class Simulation {
    public static void main(String[] args) throws InterruptedException{
        Parkhaus pk = new Parkhaus(10);

        for(int i=0;i <= 5; i++){
            Thread t = new Thread(new Auto(pk));
            t.setDaemon(true);
            t.start();
        }

        Thread.sleep(20000);
        System.out.println("Alle schlafen jetzt... Die Werwölfe erwachen!");
    }
}
