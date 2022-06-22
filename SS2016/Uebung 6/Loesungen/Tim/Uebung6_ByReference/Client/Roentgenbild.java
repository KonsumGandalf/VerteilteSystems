package SS2016.Uebung;

import java.util.Date;
import java.util.Random;

import Server.RoentgenbildIF;

public class Roentgenbild implements RoentgenbildIF{

	private Date aufnahmeVon;
	private String patientenName;
	private transient byte[] rawData;

	public Roentgenbild(){

	}

	public Roentgenbild(String patientenName, Date aufnahmeVon){
		this.patientenName = patientenName;
        this.aufnahmeVon = aufnahmeVon;
        this.rawData = erzeugeRawdata();
	}

	@Override
	public Date getAufnahmeVon() {
		return aufnahmeVon;
	}

	public void setAufnahmeVon(Date aufnahmeVon) {
		this.aufnahmeVon = aufnahmeVon;
	}

	@Override
	public String getPatientenName() {
		return patientenName;
	}

	public void setPatientenName(String patientenName) {
		this.patientenName = patientenName;
	}

	@Override
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
