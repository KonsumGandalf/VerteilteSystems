package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {

    // die Methode erhaelt jetzt etwas vom Typ RoentgenbildIF
    // (das Interface ist selbst "extends Remote" --> call-by-reference;
    //  es wird der serialisierte Stub uebertragen)
    public Bericht analysieren(RoentgenbildIF bild) throws RemoteException;
    
}
