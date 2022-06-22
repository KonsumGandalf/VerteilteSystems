package SS2016.Uebung;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements Serializable {
    
    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;

    
    // nachfolgend nur ein Konstruktor sowie Getter und Setter
    public Bericht(Date datum, String diagnose, String weiteresVorgehen) {
        this.datum = datum;
        this.diagnose = diagnose;
        this.weiteresVorgehen = weiteresVorgehen;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
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
    
    
}
