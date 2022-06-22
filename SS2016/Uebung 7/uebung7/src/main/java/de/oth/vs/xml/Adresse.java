package de.oth.vs.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Adresse {
    private String strasse;
    private String ort;
    
    public Adresse() {}

    public Adresse(String strasse, String ort) {
        this.strasse = strasse;
        this.ort = ort;
    }

    @Override
    public String toString() {
        return "Adresse{" + "strasse=" + strasse + ", ort=" + ort + '}';
    }
    
    
}
