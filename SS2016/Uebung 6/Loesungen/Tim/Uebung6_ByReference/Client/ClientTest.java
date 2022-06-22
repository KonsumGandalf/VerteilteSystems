package SS2016.Uebung;

import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;

import Server.RoentgenbildIF;
import Server.BerichtIF;

public class ClientTest {

	public static void main(String[] args) {
		try {
			Client client1 = new Client("Uniklinik Regensburg");
			Client client2 = new Client("KH Barmherzige Klinik");
			RoentgenbildIF roentgenBild1 = new Roentgenbild("Hans Huber", new Date());
			RoentgenbildIF roentgenBildStub1 = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenBild1,0);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			RoentgenbildIF roentgenBild2 = new Roentgenbild("Sepp Mayer", sdf.parse("21/12/2012"));
			RoentgenbildIF roentgenBildStub2 = (RoentgenbildIF) UnicastRemoteObject.exportObject(roentgenBild2,0);
			BerichtIF bericht1 = client1.analysieren(roentgenBildStub1);
			BerichtIF bericht2 = client2.analysieren(roentgenBildStub2);
			System.out.println("Bericht von: " + bericht1.getDatum() + "; Diagnose: " + bericht1.getDiagnose() + "; Weiteres Vorgehen: " + bericht1.getWeiteresVorgehen());
			System.out.println("Bericht von: " + bericht2.getDatum() + "; Diagnose: " + bericht2.getDiagnose() + "; Weiteres Vorgehen: " + bericht2.getWeiteresVorgehen());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
