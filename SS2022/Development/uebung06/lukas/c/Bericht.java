package Development.uebung06.lukas.c;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements Serializable {

    public Bericht(String diagnose, String weiteresVorgehen) {
        this.datum = new Date();
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

    public void printBericht(Bericht bericht) {
        System.out.println(bericht.datum + " / " + bericht.diagnose + " / " + bericht.weiteresVorgehen);
    }

    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;



}
