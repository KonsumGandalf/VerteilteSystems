package Development.uebung06.byValue;

import java.io.Serializable;
import java.util.Date;

public class Roentgenbild implements Serializable {
    private Date aufnahmeDatum;
    private transient String patientName;
    private byte[] rawData;

    public Roentgenbild(String patientName){
        this();
        this.patientName = patientName;
    }

    public Roentgenbild(){
        this.aufnahmeDatum = new Date();
        this.rawData = "Ich wäre gerne ein Bild".getBytes();
    }

    public Date getAufnahmeDatum() {
        return aufnahmeDatum;
    }

    public void setAufnahmeDatum(Date aufnahmeDatum) {
        this.aufnahmeDatum = aufnahmeDatum;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    @Override
    public String toString() {
        return "Roentgenbild{" +
                "aufnameDatum=" + this.aufnahmeDatum +
                ", patientenName='" + this.patientName + '\'' +
                ", rawData=" + this.rawData.length + " bytes" +
                '}';
    }
}
