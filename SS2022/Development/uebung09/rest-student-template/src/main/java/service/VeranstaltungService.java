package service;

import app.OTHRestException;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.*;
import de.othr.vs.xml.Veranstaltung;
import factories.VeranstaltungCollator;
import factories.VeranstaltungReducerFactory;
import factories.VeranstaltungsMapper;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import javax.print.attribute.standard.Media;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
// import java.com.hazelcast.example.mapreduce.TokenizerMapper;
import java.util.*;
import java.util.stream.Collectors;

@Path("v1")
public class VeranstaltungService {
    private static HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
    private static IMap<String, Veranstaltung> veranstaltungIMap = hzInstance.getMap("veranstaltungen");

    public VeranstaltungService() {
    }

    /* public void setVeranstaltungIMap() {
        HazelcastInstance hzInstance = Hazelcast.newHazelcastInstance();
        this.veranstaltungIMap = hzInstance.getMap("veranstaltungen");
    } */

    private static ArrayList<Veranstaltung> mapReduce(HazelcastInstance hazelcastInstance, String[] keywords){
        try{
            JobTracker jobTracker = hazelcastInstance.getJobTracker("default");

            IMap<String, Veranstaltung> map = hazelcastInstance.getMap("veranstaltungen");
            KeyValueSource<String, Veranstaltung> source = KeyValueSource.fromMap(map);

            Job<String, Veranstaltung> job= jobTracker.newJob(source);

            JobCompletableFuture<ArrayList<Veranstaltung>> future = job
                    .mapper(new VeranstaltungsMapper(keywords))
                    .reducer(new VeranstaltungReducerFactory())
                    .submit(new VeranstaltungCollator());
            // System.out.println(future.get());
            return future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @POST
    @Path("events")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Veranstaltung create(Veranstaltung veranstaltung){
        veranstaltung.setId(UUID.randomUUID().toString());
        veranstaltungIMap.put(veranstaltung.getId(), veranstaltung);
        return veranstaltung;
    }

    @GET
    @Path("events/{id}")
    public Veranstaltung getVeranstaltungById(@PathParam("id") String id){
        Veranstaltung veranstaltung = veranstaltungIMap.get(id);
        if(veranstaltung != null) return veranstaltung;
        throw new OTHRestException(404, "Veranstaltung mit ID " + id + " ist nicht eingetragen");
    }

    @GET
    @Path("events")
    public Collection<Veranstaltung> getAll(){
        return veranstaltungIMap.values();
    }

    public boolean filterKeywordList(Veranstaltung veranstaltung, String[] keywords){
        for(String keyword: keywords){
            System.out.println(keyword);
            if (veranstaltung.getTitel().contains(keyword) || veranstaltung.getBeschreibung().contains(keyword)) continue;
            System.out.println("Titel: " + veranstaltung.getTitel().contains(keyword));
            System.out.println("Desc :" + veranstaltung.getBeschreibung().contains(keyword));
            return false;
        }
        return true;
    }

    @GET
    @Path("events/search")
    @Produces({MediaType.APPLICATION_XML})
    public Collection<Veranstaltung> getRange(@QueryParam("tipps") String keywords){
        String[] keywordArray = keywords.split("\\+");
        String[] keyword2Array = keywords.split(" ");
        System.out.println(Arrays.toString(keywordArray));
        System.out.println(Arrays.toString(keyword2Array));

        ArrayList<Veranstaltung> list = mapReduce(hzInstance, keyword2Array);
        // System.out.println((Collection<Veranstaltung>) (new ArrayList<>(hzMap.values())).get(0));
        /*return veranstaltungIMap.values().stream()
                .filter(veranstaltung -> filterKeywordList(veranstaltung, keyword2Array))
                .collect(Collectors.toSet());*/
        return list.stream().toList();
    }

}

/*
class TippMapper implements Mapper<String, Veranstaltung, String, Veranstaltung>{

}*/
