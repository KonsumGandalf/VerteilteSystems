package Development.uebung06.byCallback;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Random;
import java.util.concurrent.TimeUnit;

// Inhalt stimmt, Loesung von Jobst schöner
public class FrueherkennungServer implements FrueherkennungIF {
    @Override
    public void analysieren(RoentgenbildIF roentgenbildIF, CallbackIF callbackIF){
        try {
            System.out.println("Empfangen: "+roentgenbildIF.getRepresentation());
            Bericht bericht = new Bericht("CoronaBier", "Mach dir gar kein Stress");
            BerichtIF berichtStub = (BerichtIF) UnicastRemoteObject.exportObject(bericht, 187);


            new Thread(new SleepyJoe(callbackIF, berichtStub)).start();

            System.out.println("Sende: "+berichtStub.getRepresentation());

        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}

class SleepyJoe implements Runnable {
    private CallbackIF callbackStub;
    private BerichtIF berichtStub;
    SleepyJoe(CallbackIF callbackIF, BerichtIF berichtStub){
        this.callbackStub = callbackIF;
        this.berichtStub = berichtStub;
    }
    @Override
    public void run(){
        try {
            System.out.println(0);
            TimeUnit.SECONDS.sleep(10);
            System.out.println(10);
            this.callbackStub.setBestaetigung("Bericht "+new Random().nextInt(100), berichtStub);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}