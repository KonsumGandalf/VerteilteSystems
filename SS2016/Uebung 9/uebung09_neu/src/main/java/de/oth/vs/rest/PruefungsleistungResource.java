package de.oth.vs.rest;

import de.oth.vs.xml.Pruefungsleistung;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("studienangelegenheiten")
public class PruefungsleistungResource {
    
    private static final Logger log = Logger.getLogger(PruefungsleistungResource.class.getName());

    /*
     *  1. Möglichkeit, diesen Service aufzurufen:
     *  http://<hostname>:8080/webresources/studienangelegenheiten/pruefungsleistung/
     *  HTTP-Methode "POST" und "Content-type:application/json" bzw. xml wählen und
     *  XML- oder JSON-Datenstruktur im Body mit übergeben
     */
    @POST
    @Path("pruefungsleistung")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pruefungsleistung addPruefungsleistung(Pruefungsleistung pl) {
        // interner Aufruf der überladenen Methode
        return addPruefungsleistung(pl.getPruefungId(), pl.getMatrikelNr(), pl.getVersuch(), pl.getNote());
    }

    
    /*
     *  2. Möglichkeit, diesen Service aufzurufen:
     *  http://<hostname>:8080/webresources/studienangelegenheiten/pruefungsleistung/P001/1234/1/1.0
     *  HTTP-Methode "POST" und "Content-type:application/json" bzw. xml wählen (obwohl Body leer bleibt) 
     */
    @POST
    @Path("pruefungsleistung/{pruefungId}/{matrikelNr}/{versuch}/{note}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Pruefungsleistung addPruefungsleistung(@PathParam("pruefungId") String pruefungId, @PathParam("matrikelNr") int matrikelNr, @PathParam("versuch") short versuch, @PathParam("note") String note) {

        /*
         *  Übungsblatt 8
         *  Teilaufgabe 1.2
         */
        Connection c = null;
        Pruefungsleistung pl;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            c = DriverManager.getConnection("jdbc:mysql://im-vm-011/vs-08", "vs-08", "vs-08-pw");
            // Automatischen Transaktionsabschluss fuer jedes Statement ausschalten
            c.setAutoCommit(false);
            
            // ECTS-Anzahl fuer diese Pruefung laden
            PreparedStatement statement = c.prepareStatement("SELECT ects FROM Pruefung WHERE pruefungId=?");
            statement.setString(1, pruefungId); // ersetzt das erste ? durch die pruefungId
            ResultSet result = statement.executeQuery();
            if( !result.first() ) {
                log.log(Level.SEVERE, "Pruefung mit id {0} not found", pruefungId);
                // WebApplicationException bzw. der Response.Status (HTTP-Status) steuert die Rueckgabe-Status an den Client
                throw new WebApplicationException("Pruefung mit id " + pruefungId + " not found", Response.Status.NOT_FOUND);
            }
            int ects = result.getInt("ects");
            
            // ECTS gutschreiben
            statement = c.prepareStatement("UPDATE Student SET ects=ects+? WHERE matrikelNr=?");
            statement.setInt(1, ects);
            statement.setInt(2, matrikelNr);
            int affected = statement.executeUpdate(); // falsche MatrikelNr?!?
            if(affected != 1) {
                c.rollback();
                log.severe("ECTS-Summe konnte nicht erhöht werden");
                throw new WebApplicationException("ECTS-Summe konnte nicht erhöht werden", Response.Status.CONFLICT);
            }
            
            // Note auf Gueltigkeit pruefen
            if( !(note.equals("1.0") || note.equals("1.3") || note.equals("1.7") || note.equals("2.0") || note.equals("2.3") || note.equals("2.7") || note.equals("3.0") || note.equals("3.3") || note.equals("3.7") || note.equals("4.0") || note.equals("5.0")) ) {
                c.rollback();
                log.severe("Falscher Notenwert");
                throw new WebApplicationException("Falscher Notenwert", Response.Status.CONFLICT);
            }
            // 1./2./3.-Versuch pruefen
            statement = c.prepareStatement("SELECT MAX(versuch), MIN(note) FROM Pruefungsleistung WHERE pruefungId=? AND matrikelNr=?");
            statement.setString(1, pruefungId);
            statement.setInt(2, matrikelNr);
            result = statement.executeQuery();
            if( result.first() ) {
                short maxVersuch = result.getShort("MAX(versuch)");
                String minNote = result.getString("MIN(note)");
                if(maxVersuch>2) {
                    c.rollback();
                    log.severe("Maximale Versuche überschritten");
                    throw new WebApplicationException("Maximale Versuche überschritten", Response.Status.CONFLICT);
                }
                if(maxVersuch>=versuch) {
                    c.rollback();
                    log.severe("Versuch bereits eingetragen");
                    throw new WebApplicationException("Versuch bereits eingetragen", Response.Status.CONFLICT);
                }
                if(minNote != null) {
                    if(Double.parseDouble(minNote) < 4.9) {
                        c.rollback();
                        log.severe("Gueltige Note bereits eingetragen");
                        throw new WebApplicationException("Gueltige Note bereits eingetragen", Response.Status.CONFLICT);
                    }
                }
            }

            // Pruefungsleistung eintragen
            statement = c.prepareStatement("INSERT INTO Pruefungsleistung (id, pruefungId, matrikelNr, versuch, note) VALUES (NULL, ?, ?, ?, ?)");
            statement.setString(1, pruefungId);
            statement.setInt(2, matrikelNr);
            statement.setShort(3, versuch);
            statement.setString(4, note);
            statement.execute();
            
            // Automatisch vergebene ID lesen
            statement = c.prepareStatement("SELECT LAST_INSERT_ID()");
            result = statement.executeQuery();
            result.first();
            int id = result.getInt(1);
            
            // Pruefungsleistung-Objekt erzeugen
            pl = new Pruefungsleistung(pruefungId, matrikelNr, versuch, note);
            pl.setId(id);
            
            log.log(Level.INFO, "Pruefungsleistung eingetragen mit id {0}", id);
            
            // Datenbanktransaktion abschliessen
            c.commit();
            
            // Pruefungsleistung-Objekt senden
            return pl;
            
        } catch (ClassNotFoundException e) {
            log.severe("ClassNotFound für MySQL-Treiber");
            throw new WebApplicationException("Fehler beim Laden der Daten!", Response.Status.INTERNAL_SERVER_ERROR);
        } catch(SQLException e) {
            log.log(Level.SEVERE, "Datenbankfehler: {0}", e.getLocalizedMessage());
            if(c!=null)
                try {
                    c.rollback();
            } catch (SQLException ex) {
                
            }
            throw new WebApplicationException(e.getMessage(), Response.Status.CONFLICT);
        } finally {
            try {
                if(c!=null)
                    c.close();
            } catch (SQLException ex) {
                // ignore
            }
        }
    }
    
        
}
