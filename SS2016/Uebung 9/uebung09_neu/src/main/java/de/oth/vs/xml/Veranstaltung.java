package de.oth.vs.xml;

import de.oth.vs.xml.adapter.DateAdapter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public final class Veranstaltung implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private static final String FORMAT_STRING = "dd.MM.yyyy";
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat(FORMAT_STRING);
    private String id;
    private String titel;
    private String beschreibung;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date start;
    @XmlJavaTypeAdapter(DateAdapter.class)
    private Date ende;
    private String owner;
    
    public Veranstaltung() {
        this.id = UUID.randomUUID().toString();
    }
    
    public Veranstaltung(String titel, String beschreibung, String owner) {
        this();
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.owner = owner;
        this.start = new Date();
        this.ende = new Date();
    }

    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public Date getStart() {
        // "defensive copying" von "value objects"
        // niemand, der eine Referenz auf von/bis hat, darf darüber "mein" Datum ändern
        // Datum ist ein "value object" --> http://martinfowler.com/bliki/ValueObject.html
        return new Date(start.getTime());
    }

    public void setStart(Date von) {
        // "defensive copying" von "value objects"
        this.start = new Date(von.getTime());
    }
    
    public void setStart(String von) {
        try {
            this.start = DATE_FORMAT.parse(von);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Datum " + von + " nicht im Format " + FORMAT_STRING, ex);
        }
    }

    public Date getEnde() {
        // "defensive copying" von "value objects"
        return new Date(ende.getTime());
    }

    public void setEnde(Date bis) {
        // "defensive copying" von "value objects"
        this.ende = new Date(bis.getTime());
    }

    public void setEnde(String bis) {
        try {
            this.ende = DATE_FORMAT.parse(bis);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Datum " + bis + " nicht im Format " + FORMAT_STRING, ex);
        }
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Veranstaltung{" + "id=" + id + ", titel=" + titel + ", beschreibung=" + beschreibung + ", start=" + DATE_FORMAT.format(start) + ", ende=" + DATE_FORMAT.format(ende) + ", owner=" + owner + '}';
    }


    
    public String toHtmlString() {
        String zeit = String.format("Start: %s  Ende: %s", DATE_FORMAT.format(start), DATE_FORMAT.format(ende));
        if(start.equals(ende))
            zeit = "Am: " + DATE_FORMAT.format(start);
        return String.format("<b>%s (%s)</b><br/>%n%s<br/>%n%s<br/>%n(ID: %s)<br/>%n", 
                this.titel, 
                this.owner, 
                this.beschreibung, 
                zeit,
                this.id);
    }
    
}
