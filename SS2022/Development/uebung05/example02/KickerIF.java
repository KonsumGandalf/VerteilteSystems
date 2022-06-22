package Development.uebung05.example02;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface KickerIF extends Remote {
    MatchDay reportGame(Team team) throws RemoteException;
}
