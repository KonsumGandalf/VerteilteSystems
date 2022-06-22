package de.oth.vs.rest;

import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import de.oth.vs.xml.Veranstaltung;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("studentisches")
public class VeranstaltungResource {
    
    private static final Logger log = Logger.getLogger(VeranstaltungResource.class.getName());


    @GET
    @Path("veranstaltung/tipps/{suchstring}") // suchwoerter mit + getrennt
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Veranstaltung> getVeranstaltungsTipps(@PathParam("suchstring") String suchstring) {

        IMap<String, Veranstaltung> veranstaltungen = ServerREST.hazelcast.getMap("veranstaltungen");
        
        String[] suchwoerter = suchstring.split("\\+"); // explode by +

        // über Hazelcast-MapReduce-Algorithmus nach Veranstaltungen suchen,
        // die im Titel oder in der Beschreibung mind. eines der Suchwoerter enthalten
        
        
        
        
        
        
        
    
    
        return null;
    }

    
    
    @POST
    @Path("veranstaltung")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String createVeranstaltung(Veranstaltung v) {
        IMap<String, Veranstaltung> veranstaltungen = ServerREST.hazelcast.getMap("veranstaltungen");
        
        String id = UUID.randomUUID().toString();
        v.setId(id);
        
        veranstaltungen.put(id, v);
        
        log.log(Level.INFO, "Neue Veranstaltung {0} mit id {1}", new Object[]{v.getTitel(), v.getId()});
        
        return v.getId();
    }
    
    
    
    
    @GET
    @Path("veranstaltung/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Veranstaltung getVeranstaltung(@PathParam("id") String id) {
        IMap<String, Veranstaltung> veranstaltungen = ServerREST.hazelcast.getMap("veranstaltungen");
        log.log(Level.INFO, "Veranstaltung angeforder mit id {0}", id);
        if(veranstaltungen.containsKey(id))
            return veranstaltungen.get(id);
        else
            throw new WebApplicationException(Response.Status.NOT_FOUND);
    }
    
    
}
