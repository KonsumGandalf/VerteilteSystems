package de.oth.vs.mapreduce;

import com.hazelcast.mapreduce.Reducer;
import com.hazelcast.mapreduce.ReducerFactory;
import de.oth.vs.xml.Veranstaltung;
import java.util.LinkedList;
import java.util.List;

public class TippReducerFactory implements ReducerFactory<String, Veranstaltung, List<Veranstaltung>> {
    
    @Override
    public Reducer<Veranstaltung, List<Veranstaltung>> newReducer(String keyin) {
        return new TippReducer(keyin);
    }

    
    private static class TippReducer extends Reducer<Veranstaltung, List<Veranstaltung>> {

        private List<Veranstaltung> l = new LinkedList<>();
        private String key;
        
        private TippReducer(String fuerKey) {
            this.key = fuerKey;
        }
        
        @Override
        public void reduce(Veranstaltung v) {
            if(!l.contains(v))
                l.add(v);
        }

        @Override
        public List<Veranstaltung> finalizeReduce() {
            return l;
        }
 
    }
    
}
