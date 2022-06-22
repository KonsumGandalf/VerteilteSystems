package de.lmu;

import de.lmu.server.entity.Bericht;
import de.lmu.server.entity.Roentgenbild;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    Bericht analysiere(Roentgenbild roentgenbild) throws RemoteException;
}
