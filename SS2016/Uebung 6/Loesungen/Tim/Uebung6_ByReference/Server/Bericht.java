package SS2016.Uebung;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

public class Bericht implements Externalizable, BerichtIF{

	private static final long serialVersionUID = 1L;
	private Date datum;
	private String diagnose;
	private String weiteresVorgehen;

	public Bericht(){

	}

	public Bericht(Date datum, String diagnose, String weiteresVorgehen) {
		super();
		this.datum = datum;
		this.diagnose = diagnose;
		this.weiteresVorgehen = weiteresVorgehen;
	}

	@Override
	public Date getDatum() {
		return datum;
	}
	
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	
	@Override
	public String getDiagnose() {
		return diagnose;
	}
	
	public void setDiagnose(String diagnose) {
		this.diagnose = diagnose;
	}
	
	@Override
	public String getWeiteresVorgehen() {
		return weiteresVorgehen;
	}
	
	public void setWeiteresVorgehen(String weiteresVorgehen) {
		this.weiteresVorgehen = weiteresVorgehen;
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeObject(datum);
		out.writeUTF(diagnose);
		out.writeUTF(weiteresVorgehen);
		System.out.println("Bericht wurde serialisiert!");
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.datum = (Date)in.readObject();
		this.diagnose = in.readUTF();
		this.weiteresVorgehen = in.readUTF();
		System.out.println("Bericht wurde deserialisiert!");
	}
}
