package SS2016.Uebung;

import java.text.SimpleDateFormat;
import java.util.Date;

import Server.Bericht;
import Server.Roentgenbild;

public class ClientTest {

	public static void main(String[] args) {
		try {
			Client client1 = new Client("Uniklinik Regensburg");
			Client client2 = new Client("KH Barmherzige Klinik");
			Roentgenbild roentgenBild1 = new Roentgenbild("Hans Huber", new Date());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Roentgenbild roentgenBild2 = new Roentgenbild("Sepp Mayer", sdf.parse("21/12/2012"));
			Bericht bericht1 = client1.analysieren(roentgenBild1);
			Bericht bericht2 = client2.analysieren(roentgenBild2);
			System.out.println("Bericht von: " + bericht1.getDatum() + " ; Diagnose: " + bericht1.getDiagnose() + " ; Weiteres Vorgehen: " + bericht1.getWeiteresVorgehen());
			System.out.println("Bericht von: " + bericht2.getDatum() + " ; Diagnose: " + bericht2.getDiagnose() + " ; Weiteres Vorgehen: " + bericht2.getWeiteresVorgehen());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
