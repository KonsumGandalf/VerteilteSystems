package de.lmu;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public class Bericht implements BerichtIF {

    public Bericht(String diagnose, String weiteresVorgehen) {
        System.out.println("Bericht erzeugt!");
        this.datum = new Date();
        this.diagnose = diagnose;
        this.weiteresVorgehen = weiteresVorgehen;
    }

    public Date getDatum() throws RemoteException {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getDiagnose() throws RemoteException {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public String getWeiteresVorgehen() throws RemoteException {
        return weiteresVorgehen;
    }

    public void setWeiteresVorgehen(String weiteresVorgehen) {
        this.weiteresVorgehen = weiteresVorgehen;
    }

    @Override
    public void printBericht() throws RemoteException {
        System.out.println(this.getDatum() + " / " + this.getDiagnose() + " / " + this.getWeiteresVorgehen());
        this.setDiagnose("Tumor");
    }

    private Date datum;
    private String diagnose;
    private String weiteresVorgehen;



}
