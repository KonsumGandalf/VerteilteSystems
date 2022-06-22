package de.oth.vs.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Pruefungsleistung {

    private int id;
    private String pruefungId;
    private int matrikelNr;
    private short versuch;
    private String note;

    public Pruefungsleistung() {}
        
    public Pruefungsleistung(String pruefungId, int matrikelNr, short versuch, String note) {
        this.pruefungId = pruefungId;
        this.matrikelNr = matrikelNr;
        this.versuch = versuch;
        this.note = note;
    }
    
    public void setId(int id) {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    public String getPruefungId() {
        return pruefungId;
    }

    public int getMatrikelNr() {
        return matrikelNr;
    }

    public short getVersuch() {
        return versuch;
    }

    public String getNote() {
        return note;
    }
    
    
    
    
}
