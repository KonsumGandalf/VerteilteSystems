package Development.uebung06.lukas.c;

import java.nio.charset.StandardCharsets;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client implements CallbackIF{

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry r = LocateRegistry.getRegistry("localhost", 1212);
        FrueherkennungIF server = (FrueherkennungIF) r.lookup("Test");
        Roentgenbild rb = new Roentgenbild("Max", "Lungenbeschwerden".getBytes(StandardCharsets.UTF_8));

        System.out.println(rb.getPatientenName());

        //Client Stub
        CallbackIF client = new Client();
        CallbackIF callbackStub = (CallbackIF) UnicastRemoteObject.exportObject(client,0);

        server.analysieren(rb, callbackStub);
        System.out.println(rb.getPatientenName());
    }

    @Override
    public void onBestaetigung(Bericht b) throws RemoteException {
        //b.printBericht(b);
        System.out.println("onBeststaetigung / " + b.getDiagnose() + " / " + b.getWeiteresVorgehen());
    }
}
