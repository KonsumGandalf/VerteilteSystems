package de.lmu;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class Röntgenbild implements Serializable {

    private Date aufnahmeVon;
    private transient String patientenName;
    private byte[] rawData;

    public Röntgenbild(Date aufnahmeVon, String patientenName, byte[] rawData) {
        this.aufnahmeVon = aufnahmeVon;
        this.patientenName = patientenName;
        this.rawData = rawData;
    }

    public Date getAufnahmeVon() {
        return aufnahmeVon;
    }

    public void setAufnahmeVon(Date aufnahmeVon) {
        this.aufnahmeVon = aufnahmeVon;
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

    @Override
    public String toString() {
        return "Röntgenbild{" +
                "aufnahmeVon=" + aufnahmeVon +
                ", patientenName='" + patientenName + '\'' +
                ", rawData=" + Arrays.toString(rawData) +
                '}';
    }
}
