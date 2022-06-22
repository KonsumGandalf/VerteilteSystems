package factories;

import com.hazelcast.mapreduce.Context;
import com.hazelcast.mapreduce.Mapper;
import de.othr.vs.xml.Veranstaltung;

import java.util.StringTokenizer;

public class VeranstaltungsMapper implements Mapper<String, Veranstaltung, String, Veranstaltung> {

    private String[] keywords;

    public VeranstaltungsMapper() {}
    public VeranstaltungsMapper(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public void map(String keyIn, Veranstaltung valueIn, Context<String, Veranstaltung> context){
        if(keywords != null){
            for(String keyword: keywords) {
                if (!(valueIn.getTitel().contains(keyword) || valueIn.getBeschreibung().contains(keyword))){
                    return;
                }
            }
            context.emit(valueIn.getId(), valueIn);
        }
    }


}
