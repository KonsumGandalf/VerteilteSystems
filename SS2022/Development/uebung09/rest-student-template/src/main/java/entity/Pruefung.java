package entity;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pruefung {
    @XmlAttribute
    private String pruefungId;
    private String bezeichnung;
    private int ects;

    public Pruefung() {}

    public Pruefung(String bezeichnung, int ects) {
        this.bezeichnung = bezeichnung;
        this.ects = ects;
    }
    public Pruefung(String pruefungId, String bezeichnung, int ects) {
        this(bezeichnung, ects);
        this.pruefungId = pruefungId;
    }

    public String getPruefungId() {
        return pruefungId;
    }

    public void setPruefungId(String pruefungId) {
        this.pruefungId = pruefungId;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }
}
