package de.lmu.server.api;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements Serializable {

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

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

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
