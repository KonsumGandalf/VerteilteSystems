package SS2016.Uebung;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface RoentgenbildIF extends Remote {

    public byte[] getRawData() throws RemoteException;
    public Date getAufnahmeVom() throws RemoteException;

    // getPatientenName wurde nicht als Methode definiert, da Analyse anonym erfolgen soll
    // (im Serializable-Beispiel wurde das Attribut als "transient" definiert
    //public String getPatientenName();
    
    // alle Setter wurden ebenfalls nicht als abstrakte Methoden hier definiert,
    // da Roentgenbild-Objekte schreibgeschuetzt sein sollen
    // (falls nicht, dann einfach hier definieren)

}
