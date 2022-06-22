package service;

import app.DB;
import app.OTHRestException;
import entity.Pruefung;
import entity.Pruefungsleistung;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Path("pruefungsservice")  // alternativer Pfad: @Path("studentaffairs/students") --> ersetzt dann alle @Path-Annotationen unten
public class PruefungsleistungsService {

    public ConcurrentHashMap<String, Pruefung> getDBPruefungen() {
        ArrayList<ArrayList<String>> name = DB.read("SELECT pruefungId, bezeichnung, ects FROM `Pruefung`");
        ConcurrentHashMap<String, Pruefung> pruefungen = new ConcurrentHashMap<>();
        for (int i=0; i<name.size(); i++)
        {
            pruefungen.put(name.get(i).get(0), new Pruefung(name.get(i).get(0), name.get(i).get(1), Integer.parseInt(name.get(i).get(2))));
        }
        System.out.println(pruefungen);
        return pruefungen;
    }

    public ConcurrentHashMap<String, Pruefungsleistung> getDBPruefungsleistungen() {
        ArrayList<ArrayList<String>> name = DB.read("SELECT * FROM `Pruefungsleistung`");
        ConcurrentHashMap<String, Pruefungsleistung> pruefungsleistung = new ConcurrentHashMap<>();
        for (int i=0; i<name.size(); i++)
        {
            pruefungsleistung.put(name.get(i).get(0), new Pruefungsleistung(name.get(i).get(1),
                    Integer.parseInt(name.get(i).get(2)), Integer.parseInt(name.get(i).get(3)), Double.parseDouble(name.get(i).get(4))));
        }
        System.out.println(pruefungsleistung);
        return pruefungsleistung;
    }

    @POST
    @Path("/pruefungen")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public Pruefung create(Pruefung pruefung){
        String qryString = "INSERT INTO Pruefung VALUES ("
                + "'" + pruefung.getPruefungId() + "', '"
                + pruefung.getBezeichnung() + "', "
                + pruefung.getEcts() + ");";
        System.out.println(qryString);
        boolean corrExe = DB.execute(qryString);
        if (!corrExe) System.out.println("An error occurred");
        return pruefung;
    }

    @POST
    @Path("/pruefungen/leistungen")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_XML})
    public Pruefungsleistung createLeistung(Pruefungsleistung pruefungsleistung){
        try {
            System.out.println(1);
            if (pruefungsleistung.getNote() <= 4.0) {
                System.out.println(2);
                String insertQryString = "INSERT INTO Pruefungsleistung VALUES (NULL,"
                        + "'" + pruefungsleistung.getPruefungId() + "', '"
                        + pruefungsleistung.getMatrikelNr() + "', "
                        + pruefungsleistung.getNote() + ", "
                        + pruefungsleistung.getVersuch() + ");";
                int ects = getPruefungById(pruefungsleistung.getPruefungId()).getEcts();
                String updateStudentQryString = "UPDATE Student SET ects = ects + " + ects + " WHERE matrikelNr=" + pruefungsleistung.getMatrikelNr() +";";
                System.out.println(insertQryString);
                System.out.println(updateStudentQryString);
                DB.execute(insertQryString, false);
                DB.execute(updateStudentQryString);
                return pruefungsleistung;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @DELETE
    @Path("pruefungen/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML})
    public Pruefung delete(@PathParam("id") String pruefungId) {
        System.out.println(1);
        if (getDBPruefungen().containsKey(pruefungId)){
            Pruefung returnPruefung = getPruefungById(pruefungId);
            String qryString = "DELETE FROM Pruefung WHERE pruefungId ='" + pruefungId + "';";
            DB.execute(qryString);
            return returnPruefung;
        } else {
            throw new OTHRestException(404, "Pruefung mit ID " + pruefungId + " ist nicht eingetragen");
        }
    }

    @GET
    @Path("pruefungen/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML})
    public Pruefung getPruefungById(@PathParam("id") String pruefungId){
        Pruefung searchPruefung = getDBPruefungen().get(pruefungId);
        if (searchPruefung != null) return searchPruefung;
        throw new OTHRestException(404, "Pruefung mit ID " + pruefungId + " ist nicht eingetragen");
    }

    @PUT
    @Path("pruefungen/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Pruefung updatePruefung(@PathParam("id") String pruefungId, Pruefung updatedPruefung){
        updatedPruefung.setPruefungId(pruefungId);

        if (getDBPruefungen().containsKey(pruefungId)){
            String qryString = "UPDATE Pruefung SET "+
                    "bezeichnung = '" + updatedPruefung.getBezeichnung() + "', "+
                    "ects = '" + updatedPruefung.getEcts() + "', "+
                    "WHERE matrikelNr = " + updatedPruefung.getPruefungId() +";";
            DB.execute(qryString);
            return updatedPruefung;
        } else {
            throw new OTHRestException(404, "Pruefung mit ID " + pruefungId + " ist nicht immatrikuliert");
        }
    }

    @GET
    @Path("pruefungen/all")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pruefung> getAllPruefungen() {
        return getDBPruefungen().values();
    }

    @GET
    @Path("pruefungen/leistungen")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Pruefungsleistung> getAllPruefungensleistungen() {
        return getDBPruefungsleistungen().values();
    }

}
