package entity;

import jakarta.xml.bind.annotation.*;

import java.util.Objects;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private int matrikelNr;
    private String vorname;
    private String nachname;
    private String strasse;
    private String ort;
    private int ects;

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public int getEcts() {
        return ects;
    }

    public void setEcts(int ects) {
        this.ects = ects;
    }

    // Default-Konstruktor zwingend notwendig
    public Student() {}

    public Student(String vorname, String nachname, int ects, String strasse, String ort) {
        this.vorname = vorname;
        this.nachname = nachname;
        this.ects = ects;
        this.strasse = strasse;
        this.ort = ort;
    }

    public Student(int matrikelNr, String vorname, String nachname, int ects, String strasse, String ort) {
        this(vorname, nachname, ects, strasse, ort);
        this.matrikelNr = matrikelNr;
    }

    public int getMatrikelNr() {
        return matrikelNr;
    }

    public void setMatrikelNr(int matrikelNr) {
        this.matrikelNr = matrikelNr;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return matrikelNr == student.matrikelNr;
    }

    @Override
    public int hashCode() {

        return Objects.hash(matrikelNr);
    }
}
