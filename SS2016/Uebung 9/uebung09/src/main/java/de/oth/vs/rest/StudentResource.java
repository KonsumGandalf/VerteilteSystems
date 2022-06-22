package de.oth.vs.rest;

import com.hazelcast.core.ReplicatedMap;
import de.oth.vs.xml.Adresse;
import de.oth.vs.xml.Student;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/*
  Testaufrufe z. B. mit Hilfe von Firefox und dem Add-On "restclient"
*/

@Path("studienangelegenheiten")
public class StudentResource {
    
    private static final Logger log = Logger.getLogger(StudentResource.class.getName());
    
    
    // Aufruf mit: http://localhost:8080/webresources/studienangelegenheiten/student/12345
    // HTTP-Methode GET auswählen
    // kann auch normal im Browser getestet werden
    @GET
    @Path("student/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) // beide
    //@Produces(MediaType.APPLICATION_XML) // alternativ nur eines, dann ohne {}
    public Student getStudentById(@PathParam("id") int id) throws SQLException {
        Student s = null;
        Connection c = null;
        
        
        // ReplicatedMap-Instanz über Hazelcast-Instanz holen
        // Name der Map muss lauten: "students" (damit ist nicht der Name Ihrer Variable gemeint!)
        

        
        try {
            
            // Prüfen, ob zu Matrikelnr. Objekt in Map vorhanden ist
            // falls ja, dieses zurückgeben
            
            
            
            
            
            Class.forName("com.mysql.jdbc.Driver");
            
            c = DriverManager.getConnection("jdbc:mysql://im-vm-011/vs-08", "vs-08", "vs-08-pw");
            Statement statement = c.createStatement();
            String query = "SELECT vorname, nachname, ects, strasse, ort FROM Student WHERE matrikelNr=" + id;
            ResultSet result = statement.executeQuery(query);
            if( result.first() ) {
                s = new Student(
                        id,
                        result.getString("vorname"),
                        result.getString("nachname"),
                        result.getInt("ects"),
                        new Adresse(
                                result.getString("strasse"),
                                result.getString("ort")
                        ));
            } else {
                // WebApplicationException steuern die HTTP-Status-Codes (siehe Response.Status-Enumeration)
                throw new WebApplicationException(id + " not found", Response.Status.NOT_FOUND);
            }
            c.close();
            
            
            // Student der Hazelcast-Map hinzufügen (gültig für 5 Minuten)

            
            
            
            return s;
        } catch (ClassNotFoundException e) {
            throw new WebApplicationException("Fehler beim Laden der Daten!", Response.Status.INTERNAL_SERVER_ERROR);
        } catch(SQLException e) {
            throw new WebApplicationException(Response.Status.SERVICE_UNAVAILABLE);
        }

        
    }

    // Aufruf mit: http://localhost:8080/webresources/studienangelegenheiten/student
    // HTTP-Methode DELETE auswählen
    @DELETE
    @Path("student/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void deleteStudentById(@PathParam("id") int id) {
        System.out.println("delete Student with id " + id);
    }

    // Aufruf mit: http://localhost:8080/webresources/studienangelegenheiten/student
    // HTTP-Methode POST und custom header "Content-Type: application/xml" oder application/json setzen
    // Als Content entsprechend XML- oder JSON-Objekt uebergeben
    @POST
    @Path("student")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void createNewStudent(Student s) {
        System.out.println("create new student " + s);
    }

    // Aufruf mit: http://localhost:8080/webresources/studienangelegenheiten/student
    // HTTP-Methode PUT und custom header "Content-Type: application/xml" oder application/json setzen
    // Als Content entsprechend XML- oder JSON-Objekt uebergeben
    @PUT
    @Path("student")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public void updateNewStudent(Student s) {
        System.out.println("updated student " + s);
    }

    // Aufruf mit: http://localhost:8080/webresources/studienangelegenheiten/students?from=1&to=19
    @GET
    @Path("students")
    @Produces(MediaType.APPLICATION_JSON)
    public Student[] getStudentsByIdRange(@QueryParam("from") int from, @QueryParam("to") int to) {
        if(to < from)
            return new Student[0];
        Student[] students = new Student[(to-from)+1];
        for(int id = from; id <= to; id++) {
            students[id - from] = new Student(id, "Vorname_"+id, "Nachname_"+id, new Random().nextInt(210), new Adresse("Strasse_"+id, "Ort_"+id));
        }
        return students;
    }
    
}
