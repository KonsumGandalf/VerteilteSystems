package Development.uebung05.example02;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args){
        Team t1 = new Team("SSV Jahn Rgbg");
        System.out.println("Send: \n"+t1);

        try{
            Registry r = LocateRegistry.getRegistry("localhost", 2001);
            KickerIF stub = (KickerIF) r.lookup("Kicker");
            MatchDay response = stub.reportGame(t1);

            System.out.println("Received: \n"+response);
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}
