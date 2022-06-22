package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pruefungsleistung {
    public Pruefungsleistung(){}
    public Pruefungsleistung(String pruefungId, int matrikelNr, int versuch, double note) {
        this.pruefungId = pruefungId;
        this.matrikelNr = matrikelNr;
        this.versuch = versuch;
        this.note = note;
    }

    public String getPruefungId() {
        return pruefungId;
    }

    public void setPruefungId(String pruefungId) {
        this.pruefungId = pruefungId;
    }

    public int getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public int getVersuch() {
        return versuch;
    }

    public void setVersuch(int versuch) {
        this.versuch = versuch;
    }

    public double getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @XmlAttribute
    private String pruefungId;
    private int matrikelNr;
    private int versuch;
    private double note;
}
