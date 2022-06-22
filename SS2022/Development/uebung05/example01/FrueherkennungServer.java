package Development.uebung05.example01;

public class FrueherkennungServer implements FrueherkennungIF{
    @Override
    public Bericht analysieren(Roentgenbild r){
        System.out.println("Empfangen: "+r.toString());

        Bericht antwort = new Bericht("CoronaBier", "Mach dir gar kein Stress");

        System.out.println("Sende: "+antwort.toString());

        return antwort;
    }
}
