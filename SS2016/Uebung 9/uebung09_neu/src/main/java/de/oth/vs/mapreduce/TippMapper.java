package de.oth.vs.mapreduce;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import de.oth.vs.xml.Veranstaltung;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class TippMapper implements Mapper<String, Veranstaltung, String, Veranstaltung> {
    private List<String> suchwoerter;
    private Date jetzt = new Date();

    public TippMapper(List<String> suchwoerter) {
        this.suchwoerter = suchwoerter;
    }
    
    @Override
    public void map(String key, Veranstaltung v, Context<String, Veranstaltung> context) {
        
        //if(v.getEnde().before(jetzt)) // sofern Veranstaltung bereits in Vergangenheit liegt, ignorieren!
        //    return;
        
        for(String suchwort : suchwoerter) {
            if(v.getBeschreibung().contains(suchwort)
                || v.getTitel().contains(suchwort)) {
                context.emit(suchwort, v);
            }
        }
    }
    
}
