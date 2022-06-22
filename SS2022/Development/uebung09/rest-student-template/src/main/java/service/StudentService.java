package service;

import app.DB;
import app.OTHRestException;

import de.othr.vs.xml.Student;
import de.othr.vs.xml.Adresse;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Path("studentservice")  // alternativer Pfad: @Path("studentaffairs/students") --> ersetzt dann alle @Path-Annotationen unten
public class StudentService {

    /* private static AtomicInteger studentId = new AtomicInteger(1);
    private static ConcurrentHashMap<Integer, Student> studentDB = new ConcurrentHashMap<>();*/

    public ConcurrentHashMap<Integer, Student> getDBStudents() {
        ArrayList<ArrayList<String>> name = DB.read("SELECT matrikelNr, vorname, nachname, ects, strasse, ort FROM `Student`");
        ConcurrentHashMap<Integer, Student> students = new ConcurrentHashMap<>();
        for (int i=0; i<name.size(); i++)
        {
            Random r = new Random(); // PLZ is not defined in the Database
            Adresse studentAdresse = new Adresse(name.get(i).get(4), ""+(10000+r.nextInt(89999)), name.get(i).get(5));
            students.put(Integer.parseInt(name.get(i).get(0)), new Student(Integer.parseInt(name.get(i).get(0)), name.get(i).get(1), name.get(i).get(2),
                    Integer.parseInt(name.get(i).get(3)), studentAdresse));
        }
        return students;
    }

    @POST
    @Path("/students")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student matriculate(Student student){
        String qryString = "INSERT INTO Student VALUES ("
                + student.getMatrikelNr() + ", '"
                + student.getVorname() + "', '"
                + student.getNachname() + "', "
                + student.getEcts() + ", '"
                + student.getAnschrift().getStrasse() + "', '"
                + student.getAnschrift().getOrt() + "')";
        System.out.println(qryString);
        boolean corrExe = DB.execute(qryString);
        if (!corrExe) System.out.println("An error occurred");
        return student;
    }

    @DELETE
    @Path("students/{id}")
    public Student exmatriculate(@PathParam("id") int matrikelNr) {
        if (getDBStudents().containsKey(matrikelNr)){
            Student returnStudent = getStudentById(matrikelNr);
            String qryString = "DELETE FROM Student WHERE matrikelNr=" + matrikelNr + ";";
            DB.execute(qryString);
            return returnStudent;
        } else {
            throw new OTHRestException(404, "Student mit ID " + matrikelNr + " ist nicht immatrikuliert");
        }
    }

    @GET
    @Path("students/{id}")
    public Student getStudentById(@PathParam("id") int studentId){
        Student searchStudent = getDBStudents().get(studentId);

        System.out.println(searchStudent);
        if (searchStudent != null) return searchStudent;
        throw new OTHRestException(404, "Student mit ID " + studentId + " ist nicht immatrikuliert");
    }

    @PUT
    @Path("students/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Student updateStudent(@PathParam("id") int studentId, Student updatedStudent){
        updatedStudent.setMatrikelNr(studentId);

        if (getDBStudents().containsKey(studentId)){
            String qryString = "UPDATE Student SET "+
                    "vorname = '" + updatedStudent.getVorname() + "', "+
                    "nachname = '" + updatedStudent.getNachname() + "', "+
                    "ects = " + updatedStudent.getEcts() + ", "+
                    "strasse = '" + updatedStudent.getAnschrift().getStrasse() + "', '"+
                    "ort = '" + updatedStudent.getAnschrift().getOrt() + "' " +
                    "WHERE matrikelNr = " + updatedStudent.getMatrikelNr() +";";
            System.out.println(qryString);
            boolean corrExe = DB.execute(qryString);
            System.out.println(corrExe);
            return updatedStudent;
        } else {
            throw new OTHRestException(404, "Student mit ID " + studentId + " ist nicht immatrikuliert");
        }
    }

    @GET
    @Path("students/all")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON})
    public Collection<Student> getAllStudents() {
        return getDBStudents().values();
    }

    @GET
    @Path("students")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public Collection<Student> getStudentRange(@QueryParam("from") int lowerNr, @QueryParam("to") int topNr){
        return getDBStudents().values().stream()
                .filter(student -> student.getMatrikelNr() >= lowerNr && student.getMatrikelNr() <=topNr)
                .collect(Collectors.toSet());
    }
}
