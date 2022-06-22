package SS2016.Uebung;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Roentgenbild implements Serializable{

	public Roentgenbild(String patientenName, Date aufnahmeVon){
		this.patientenName = patientenName;
        this.aufnahmeVon = aufnahmeVon;
        this.rawData = erzeugeRawdata();
	}

	private static final long serialVersionUID = 1L;
	private Date aufnahmeVon;
	private String patientenName;
	private byte[] rawData;

	public Date getAufnahmeVon() {
		return aufnahmeVon;
	}
	
	public void setAufnahmeVon(Date aufnahmeVon) {
		this.aufnahmeVon = aufnahmeVon;
	}
	
	public String getPatientenName() {
		return patientenName;
	}
	
	public void setPatientenName(String patientenName) {
		this.patientenName = patientenName;
	}
	
	public byte[] getRawData() {
		return rawData;
	}
	
	public void setRawData(byte[] rawData) {
		this.rawData = rawData;
	}

	private byte[] erzeugeRawdata() {
        int size = new Random().nextInt(1000000);
        byte[] raw = new byte[size];
        new Random().nextBytes(raw);
        return raw;
    }
}
