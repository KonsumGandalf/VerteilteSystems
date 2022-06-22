package Development.uebung06.lukas.c;

import java.rmi.RemoteException;

public class Analyse implements Runnable{

    Roentgenbild rb = null;
    CallbackIF cb = null;

    public Analyse(Roentgenbild rb, CallbackIF cb){
        this.rb = rb;
        this.cb = cb;
    }

    @Override
    public void run() {
        try {
            Bericht bericht = new Bericht("Krebs", "Chemo");
            Thread.sleep(5000);
            cb.onBestaetigung(bericht);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
