package SS2016.Uebung;

import java.io.Serializable;
import java.util.Date;

public class Bericht implements Serializable{

	private static final long serialVersionUID = 1L;
	private Date datum;
	private String diagnose;

	public Bericht(){

	}

	public Bericht(Date datum, String diagnose, String weiteresVorgehen) {
		super();
		this.datum = datum;
		this.diagnose = diagnose;
		this.weiteresVorgehen = weiteresVorgehen;
	}

	private String weiteresVorgehen;

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
