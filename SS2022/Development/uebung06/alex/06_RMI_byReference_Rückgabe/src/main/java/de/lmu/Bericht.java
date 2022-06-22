package de.lmu;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements BerichtIF {

    private Date date;
    private String diagnose;
    private String weiteresVorgehen;

    public Bericht(){}

    public Bericht(Date date, String diagnose, String weiteresVorgehen) {
        this.date = date;
        this.diagnose = diagnose;
        this.weiteresVorgehen = weiteresVorgehen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
                "date=" + date +
                ", diagnose='" + diagnose + '\'' +
                ", weiteresVorgehen='" + weiteresVorgehen + '\'' +
                '}';
    }
}
