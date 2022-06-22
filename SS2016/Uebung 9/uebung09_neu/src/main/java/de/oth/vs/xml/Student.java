package de.oth.vs.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlAttribute
    private int matrikelNr;
    private String vorname;
    private String nachname;
    private int ects;
    private Adresse anschrift;
    
    public Student() {}

    public Student(int sozialversicherungsNummer, String vorname, String nachname, int ects, Adresse anschrift) {
        this.matrikelNr = sozialversicherungsNummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.ects = ects;
        this.anschrift = anschrift;
    }

    @Override
    public String toString() {
        return "Student{" + "matrikelNr=" + matrikelNr + ", vorname=" + vorname + ", nachname=" + nachname + ", ects=" + ects + ", anschrift=" + anschrift + '}';
    }

    
}
