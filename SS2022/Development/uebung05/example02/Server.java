package Development.uebung05.example02;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args){
        KickerIF kicker = new KickerIFService();
        try{
            KickerIF stub = (KickerIF) UnicastRemoteObject.exportObject(kicker, 2001);
            Registry registry = LocateRegistry.createRegistry(2001);
            registry.bind("Kicker", stub);
            System.out.println("Server hochgefahren, warte auf Anfragen");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
