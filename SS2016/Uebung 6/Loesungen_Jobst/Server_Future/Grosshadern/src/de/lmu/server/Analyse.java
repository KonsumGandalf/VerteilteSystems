package SS2016.Uebung;

import de.lmu.AnalyseIF;
import de.lmu.Bericht;
import de.lmu.Roentgenbild;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;


public class Analyse implements AnalyseIF, Runnable {
    
    private static int LETZTE_LFD_NR = 1;
    private final Roentgenbild bild;
    private final int analyseNr;
    private Bericht ergebnis = null;
    private boolean abgebrochen = false;
    
    public Analyse(Roentgenbild bild) {
        this.bild = bild;
        analyseNr = LETZTE_LFD_NR++;
    }
    
    @Override
    public void run() {
        int dauer = new Random().nextInt(10) + 1;
        
        System.out.println("Roentgenbild vom " + bild.getAufnahmeVom() + " wird jetzt analysiert. Dauer voraussichtlich " + dauer + " Sek.");
        try {
            TimeUnit.SECONDS.sleep( dauer );
        } catch (InterruptedException ex) {
            
        }
        
        this.ergebnis = new Bericht( new Date(), "Vorsorgeuntersuchung, A.v. Malign. [Analyse Nr. " + analyseNr + "]", "Wiedervorstellung in 2 Jahren erbeten");

        System.out.println("Analyse von Roentgenbild vom " + bild.getAufnahmeVom() + " ist abgeschlossen. Future kann jetzt abholen.");
    }

    
    @Override
    public Bericht abholen() throws RemoteException {
        return ergebnis;
    }

    @Override
    public boolean isAbholbereit() throws RemoteException {
        return (ergebnis != null && this.abgebrochen != true);
    }

    @Override
    public void abbrechen() throws RemoteException {
        this.abgebrochen = true;
    }
    
}
