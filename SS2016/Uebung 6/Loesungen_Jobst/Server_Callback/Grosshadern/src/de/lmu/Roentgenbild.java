package SS2016.Uebung;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Roentgenbild implements Serializable {
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

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public String getPatientenName() {
        return patientenName;
    }

    public void setPatientenName(String patientenName) {
        this.patientenName = patientenName;
    }

    public Date getAufnahmeVom() {
        return aufnahmeVom;
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
