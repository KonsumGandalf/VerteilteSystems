package Development.uebung06.lukas.b;

import java.io.Serializable;
import java.util.Date;

public class Roentgenbild implements Serializable {
    private Date aufnahmeVom;
    //patientName cannot be serialized
    private transient String patientenName;
    private byte[] rawData;

    public Roentgenbild(String patientenName, byte[] rawData) {
        this.aufnahmeVom = new Date();
        setPatientenName(patientenName);
        setRawData(rawData);
    }

    public Date getAufnahmeVom() {
        return aufnahmeVom;
    }

    public void setAufnahmeVom(Date aufnahmeVom) {
        this.aufnahmeVom = aufnahmeVom;
    }

    public String getPatientenName() {
        return patientenName;
    }

    public void setPatientenName(String patientenName) {
        this.patientenName = patientenName;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }
}
