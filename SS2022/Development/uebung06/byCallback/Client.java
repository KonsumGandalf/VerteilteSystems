package Development.uebung06.byCallback;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Client implements CallbackIF {
    Map<String, BerichtIF> sucCallback = new TreeMap<>();
    public static void main(String[] args) {

        System.out.println("Frueherkennung starten");
        Roentgenbild roentgenbild = new Roentgenbild("Ron");

        try{
            Registry r = LocateRegistry.getRegistry("localhost", 187);

            FrueherkennungIF serverStub = (FrueherkennungIF) r.lookup("Frueherkennung");
            RoentgenbildIF bildStub = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenbild, 0);

            CallbackIF client = new Client();
            CallbackIF callbackStub = (CallbackIF) UnicastRemoteObject.exportObject(client, 0);

            serverStub.analysieren(bildStub, callbackStub);

            System.out.println("Nothing received yet");
            // new Thread(new SleepyJoe()).start();
            // System.out.println("Bericht empfangen: " + berichtStub.getRepresentation());
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }


    }
    public void setBestaetigung(String name, BerichtIF berichtIF) throws RemoteException{
        sucCallback.put(name, berichtIF);
        for (BerichtIF bericht: sucCallback.values()){
            System.out.println(bericht.getRepresentation());
        }
    }

}
