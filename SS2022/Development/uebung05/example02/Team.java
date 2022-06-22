package Development.uebung05.example02;

import java.io.Serializable;
import java.util.Random;

public class Team implements Serializable {
    private String teamName;
    private int memberCount;
    private transient int foundationYear;

    public Team(String teamName) {
        this();
        this.teamName = teamName;
        this.foundationYear = 1923;
    }

    public Team(){
        this.memberCount = new Random().nextInt(900000);
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(int foundationYear) {
        this.foundationYear = foundationYear;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", memberCount=" + memberCount +
                ", foundationYear=" + foundationYear +
                '}';
    }
}
