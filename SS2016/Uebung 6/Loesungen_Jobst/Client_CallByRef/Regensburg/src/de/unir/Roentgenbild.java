package SS2016.Uebung;

import de.lmu.RoentgenbildIF;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Random;

public class Roentgenbild implements RoentgenbildIF {
    private byte[] rawData;
    private transient String patientenName;
    private Date aufnahmeVom;

    // nachfolgend nur ein Konstruktor sowie Getter und Setter
    // und eine Hilfsmethode zur Erzeugung der Rohdaten
    public Roentgenbild(String patientenName, Date aufnahmeVom) {
        this.patientenName = patientenName;
        this.aufnahmeVom = aufnahmeVom;
        this.rawData = erzeugeRawdata();
    }

    // Methoden, die man wegen RoentgenbildIF implementieren muss
    @Override
    public byte[] getRawData() throws RemoteException {
        System.out.println("getRawData() wurde vom Server jetzt aufgerufen; returned " + rawData.length + " bytes.");
        return rawData;
    }
    
    @Override
    public Date getAufnahmeVom() throws RemoteException {
        System.out.println("getAufnahmeVom() wurde vom Server jetzt aufgerufen; returned Datum " + aufnahmeVom.toString());
        return aufnahmeVom;
    }

    // weitere Hilfsmethoden fuer den Client
    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getPatientenName() {
        return patientenName;
    }

    public void setPatientenName(String patientenName) {
        this.patientenName = patientenName;
    }


    public void setAufnahmeVom(Date aufnahmeVom) {
        this.aufnahmeVom = aufnahmeVom;
    }
    
    private byte[] erzeugeRawdata() {
        int size = new Random().nextInt(1000000);
        byte[] raw = new byte[size];
        new Random().nextBytes(raw);
        return raw;
    }
}
