package de.lmu.server.entity;

import de.lmu.server.api.BerichtIF;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements BerichtIF {

    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;

    public Bericht(String diagnose, String weiteresVorgehen) {
        this();
        this.diagnose = diagnose;
        this.weiteresVorgehen = weiteresVorgehen;
    }

    public Bericht() {
        this.datum = new Date();
    }

    @Override
    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    @Override
    public String getDiagnose() {
        System.out.println("getDiagnose() aufgerufen!");
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    @Override
    public String getWeiteresVorgehen() {
        return weiteresVorgehen;
    }

    public void setWeiteresVorgehen(String weiteresVorgehen) {
        this.weiteresVorgehen = weiteresVorgehen;
    }

    @Override
    public String toString() {
        return "Bericht{" +
                "datum=" + datum +
                ", diagnose='" + diagnose + '\'' +
                ", weiteresVorgehen='" + weiteresVorgehen + '\'' +
                '}';
    }
}
