package de.oth.vs.xml;


import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;

public class MarshallTest {

    public static void main(String[] args) throws JAXBException {
        Student stud = new Student(1234, "Max", "Muster", 87, new Adresse("Unistr. 1", "Ortingen"));
       
        // XML in Datei schreiben
        JAXB.marshal(stud, new File("stud1234.xml"));

        // XML in String schreiben (geht nur über StringWriter) ...
        StringWriter sw = new StringWriter();
        JAXB.marshal(stud, sw);
        System.out.println(sw.toString());
        
        // ... und den String (XML) wieder in neues Objekt umwandeln lassen
        Student neu = JAXB.unmarshal(new StringReader(sw.toString()), Student.class);
        // toString() des neuen Objekts aufrufen
        System.out.println(neu);
        
        // Normale Ausgabe auf System.out
        JAXB.marshal(neu, System.out);
        
    }
}
