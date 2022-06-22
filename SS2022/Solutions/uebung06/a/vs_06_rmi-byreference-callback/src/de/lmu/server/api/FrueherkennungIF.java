package de.lmu.server.api;

import de.lmu.server.entity.Roentgenbild;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FrueherkennungIF extends Remote {
    Bericht analysiere(RoentgenbildIF roentgenbild) throws RemoteException;
}
