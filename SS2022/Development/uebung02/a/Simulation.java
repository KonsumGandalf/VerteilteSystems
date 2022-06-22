package Development.uebung02.a;

public class Simulation {
    public static void main(String[] args) throws InterruptedException {
        KitchenCounter mensaOth = new KitchenCounter(4);

        for (int i = 1; i <= 2; i++) {
            Thread t = new Thread(new Waiter(mensaOth));
            t.setDaemon(true);
            t.start();
        }
        for (int i = 1; i <= 8; i++) {
            Thread t = new Thread(new Student(mensaOth));
            t.setDaemon(true);
            t.start();
        }

        Thread.sleep(20000);
        System.out.println("Alle schlafen jetzt... Die Werwölfe erwachen!");
    }
}
