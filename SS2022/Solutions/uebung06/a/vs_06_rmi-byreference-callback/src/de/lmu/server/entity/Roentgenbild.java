package de.lmu.server.entity;

import de.lmu.server.api.RoentgenbildIF;

import java.io.Serializable;
import java.util.Date;

public class Roentgenbild implements RoentgenbildIF {

    private Date aufnameDatum;
    private transient String patientenName;
    private byte[] rawData;

    public Roentgenbild(String patientenName) {
        this();
        this.patientenName = patientenName;
    }

    public Roentgenbild() {
        this.aufnameDatum = new Date();
        this.rawData = "Ich wäre gerne ein Bild".getBytes();
    }

    @Override
    public Date getAufnameDatum() {
        System.out.println("getAufnahmeDatum() aufgerufen!");
        return aufnameDatum;
    }

    public void setAufnameDatum(Date aufnameDatum) {
        this.aufnameDatum = aufnameDatum;
    }

    @Override
    public String getPatientenName() {
        return patientenName;
    }

    public void setPatientenName(String patientenName) {
        this.patientenName = patientenName;
    }

    @Override
    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    @Override
    public String toString() {
        return "Roentgenbild{" +
                "aufnameDatum=" + aufnameDatum +
                ", patientenName='" + patientenName + '\'' +
                ", rawData=" + rawData.length + " bytes" +
                '}';
    }
}
