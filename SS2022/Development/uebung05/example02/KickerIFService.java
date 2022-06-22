package Development.uebung05.example02;

import java.rmi.RemoteException;
import java.util.Date;

public class KickerIFService implements KickerIF {

    @Override
    public MatchDay reportGame(Team team) throws RemoteException {
        System.out.println("Empfangen: " + team.toString());

        MatchDay md = new MatchDay(team.getTeamName(), new Date());

        System.out.println("Sende: " + md);
        return md;
    }
}
