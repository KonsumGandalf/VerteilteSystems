package entity;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Adresse {
    protected String stasse;
    protected String ort;
    protected int plz;

    public String getStasse() {
        return stasse;
    }

    public void setStasse(String stasse) {
        this.stasse = stasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public Adresse(String stasse, String ort, int plz) {
        this.stasse = stasse;
        this.ort = ort;
        this.plz = plz;
    }

    public Adresse() {}
}
