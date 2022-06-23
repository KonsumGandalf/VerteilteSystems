package factories;


import com.hazelcast.mapreduce.Collator;
import de.othr.vs.xml.Veranstaltung;

import java.util.ArrayList;
import java.util.Map;

public class VeranstaltungCollator implements Collator<Map.Entry<String, ArrayList<Veranstaltung>>, ArrayList<Veranstaltung>> {
    @Override
    public ArrayList<Veranstaltung> collate(Iterable<Map.Entry<String, ArrayList<Veranstaltung>>> veranstaltungenIn) {
        ArrayList<Veranstaltung> veranstaltungenOut = new ArrayList<Veranstaltung>();
        System.out.println(veranstaltungenOut);
        for (Map.Entry<String, ArrayList<Veranstaltung>> veranstaltungList: veranstaltungenIn){
            for (Veranstaltung veranstaltungEntry: veranstaltungList.getValue()) {
                if(!veranstaltungenOut.contains(veranstaltungEntry)) veranstaltungenOut.add(veranstaltungEntry);
            }
        }
        System.out.println(veranstaltungenOut);

        return veranstaltungenOut;
    }
}
