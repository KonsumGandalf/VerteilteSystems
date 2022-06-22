package de.lmu.server.api;

import de.lmu.server.entity.Bericht;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    BerichtIF analysiere(Roentgenbild roentgenbild) throws RemoteException;
}
