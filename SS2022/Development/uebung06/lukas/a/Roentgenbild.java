package Development.uebung06.lukas.a;

import java.rmi.RemoteException;
import java.util.Date;

public class Roentgenbild implements RoentgenbildIF {
    private Date aufnahmeVom;
    //patientName cannot be serialized
    private transient String patientenName;
    private byte[] rawData;

    public Roentgenbild(String patientenName, byte[] rawData)  throws RemoteException{
        this.aufnahmeVom = new Date();
        setPatientenName(patientenName);
        setRawData(rawData);
    }

    @Override
    public Date getAufnahmeVom() throws RemoteException {
        return aufnahmeVom;
    }

    @Override
    public void setAufnahmeVom(Date aufnahmeVom) throws RemoteException{
        this.aufnahmeVom = aufnahmeVom;
    }

    @Override
    public String getPatientenName() throws RemoteException{
        return patientenName;
    }

    @Override
    public void setPatientenName(String patientenName) throws RemoteException{
        this.patientenName = patientenName;
    }

    @Override
    public byte[] getRawData() throws RemoteException{
        return rawData;
    }

    @Override
    public void setRawData(byte[] rawData) throws RemoteException{
        this.rawData = rawData;
    }

    @Override
    public void print_Roentgenbild() throws RemoteException{
        System.out.println(this.aufnahmeVom + " / " + this.patientenName + " / " + this.rawData);
    }
}
