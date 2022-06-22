package SS2016.Uebung;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Cheque implements Externalizable {
	
	private static final long serialVersionUID = 1L;
	private String bank;
	private String bic;
	private String kontonr;
	private int betrag;
	private String vermerke;
	
	// Default-Konstruktor unbedingt nötig!!
	public Cheque() {
		
	}
	
	public Cheque(int betrag, String kontonr, String bic) {
		this.betrag = betrag;
		this.kontonr = kontonr;
		this.bic = bic;
	}
	
	public Cheque(int betrag, String kontonr, String bic, String bank, String vermerke) {
		this(betrag, kontonr, bic);
		this.bank = bank;
		this.vermerke = vermerke;
	}
	
	
	//@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(betrag);
		out.writeObject(bic);
		out.writeObject(kontonr);
		System.out.println("Cheque wurde serialisiert!");
		// Rest wird nicht serialisiert
	}
	//@Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.betrag = in.readInt();
		this.bic = (String)in.readObject();
		this.kontonr = (String)in.readObject();
		System.out.println("Cheque wurde deserialisiert (eingelesen)!");
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBic() {
		return bic;
	}
	public void setBic(String bic) {
		this.bic = bic;
	}
	public String getKontonr() {
		return kontonr;
	}
	public void setKontonr(String kontonr) {
		this.kontonr = kontonr;
	}
	public int getBetrag() {
		return betrag;
	}
	public void setBetrag(int betrag) {
		this.betrag = betrag;
	}
	public String getVermerke() {
		return vermerke;
	}
	public void setVermerke(String vermerke) {
		this.vermerke = vermerke;
	}
	
	@Override
	public String toString() {
		return String.format("[%.2f Euro, Konto:%s, BIC:%s]", (double)this.betrag/100, this.kontonr, this.bic);
	}
}
