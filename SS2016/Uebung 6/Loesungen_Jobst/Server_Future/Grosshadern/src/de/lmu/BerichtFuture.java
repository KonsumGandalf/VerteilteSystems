package SS2016.Uebung;

import java.io.Serializable;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



public class BerichtFuture implements Future<Bericht>, Serializable {

    private boolean istAbgebrochen = false;
    private final AnalyseIF analyseStub;
    

    public BerichtFuture(AnalyseIF analyseStub) {
        this.analyseStub = analyseStub;
    }
    
    
    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        try {
            analyseStub.abbrechen();
            istAbgebrochen = true;
        } catch (RemoteException ex) {
            // 
        }
        return true;
    }

    @Override
    public boolean isCancelled() {
        return this.istAbgebrochen;
    }

    @Override
    public boolean isDone() {
        try {
            return this.analyseStub.isAbholbereit();
        } catch (RemoteException ex) {
            return false;
        }
    }

    @Override
    public Bericht get() throws InterruptedException, ExecutionException {
        Bericht bericht = null;
        try {
            bericht = analyseStub.abholen();
        } catch (AccessException ex) {
            // 
        } catch (RemoteException ex) {
            // 
        }    
        
        return bericht;
    }

    @Override
    public Bericht get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException("Fuer Beispiel nicht implementiert.");
    }


}
