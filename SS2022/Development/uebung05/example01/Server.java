package Development.uebung05.example01;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {

    public static void main(String[] args){
        FrueherkennungIF serverImpl = new FrueherkennungServer();
        try{
            FrueherkennungIF stub = (FrueherkennungIF) UnicastRemoteObject.exportObject(serverImpl, 0);
            Registry r = LocateRegistry.createRegistry(187);
            r.bind("Frueherkennung", stub);
            System.out.println("Server hochgefahren, warte auf Anfragen");

        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
