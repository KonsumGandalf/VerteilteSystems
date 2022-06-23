package factories;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;
import de.othr.vs.xml.Veranstaltung;

import java.util.ArrayList;
import java.util.List;

public class VeranstaltungReducerFactory implements ReducerFactory<String, Veranstaltung, ArrayList<Veranstaltung>> {

    @Override
    public Reducer<Veranstaltung, ArrayList<Veranstaltung>> newReducer(String key) {
        return new VeranstaltungReducer();
    }

    private class VeranstaltungReducer extends Reducer<Veranstaltung, ArrayList<Veranstaltung>> {

        private volatile List<Veranstaltung>  veranstaltungen = new ArrayList<Veranstaltung>();

        @Override
        public void reduce(Veranstaltung veranstaltung) {
            veranstaltungen.add(veranstaltung);
        }

        @Override
        public ArrayList<Veranstaltung> finalizeReduce() {
            return null;
        }
    }

}
