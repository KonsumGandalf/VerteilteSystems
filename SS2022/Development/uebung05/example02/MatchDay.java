package Development.uebung05.example02;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class MatchDay implements Serializable {
    private String team1;
    private String team2;
    private String stadium;
    private String score;
    private transient int spectators;
    private Date date;

    public MatchDay(String team1, Date d) {
        this();
        this.team1 = team1;
        this.date = d;
    }

    public MatchDay(){
        Random r = new Random();
        this.score = r.nextInt(8) + " - " + r.nextInt(8);
        this.stadium = new String[]{"Westfalenstadion", "Olympiastadion Berlin", "Veltins-Arena", "Mercedes-Benz Arena"}[r.nextInt(3)];
        this.team2 = new String[]{"BVB", "Hertha BSC Berlin", "Schalke 04", "Stuttgart"}[r.nextInt(3)];
        this.spectators = r.nextInt(40000);
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getSpectators() {
        return spectators;
    }

    public void setSpectators(int spectators) {
        this.spectators = spectators;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Match{" +
                "\nSpieltag=" + this.date +
                "\nTeam1=" + this.team1 + " " + this.score  + " " + this.team2 + "=Team2" +
                "\nSpectators='" + this.spectators + " in the arena of " + this.stadium +
                '}';
    }
}
