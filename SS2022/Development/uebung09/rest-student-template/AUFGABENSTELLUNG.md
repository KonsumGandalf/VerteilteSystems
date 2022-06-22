# Aufgabenstellung: Implementierung einer RESTful-Webservices-API für einen "Student Service"

Nutzen Sie für die Aufgabenstellung das vorliegende Projekttemplate und führen Sie die vorbereitenden Tätigkeiten aus,
wie sie in der [README-Datei](README.md) beschrieben sind.

## Mapping von Entitätsklassen auf XML und JSON (XML- und JSON-Binding)

Im Package `entity` befindet sich die Entitäts-Klasse `Student`. Diese ist mit der Annotation `@XmlRootElement` 
versehen und hat einen Konstruktor ohne Parameter. Erklären Sie, wofür beide notwendig sind und erklären Sie auch 
die Wirkungsweise der weiteren Annotationen `@XmlAccessorType`, `@XmlAttribute` oder `@XmlTransient` für ein XML-Binding.

Erstellen Sie eine weitere Klasse `Adresse` mit den Attributen `strasse`, `plz` und `ort`.

Die Klasse `Student` soll um ein Attribut `anschrift` des Typs `Adresse` ergänzt werden.

## JAX-RS-Annotationen

Die Methodenrümpfe der Klasse `StudentService` im Package `service` sind bereits vorgegeben. 
Implementieren Sie für jede vorgegebene Methode jeweils eine simple Funktionalität, die die übergebenen Objekte zum Beispiel in einer statischen Liste (als "interne Datenbank") verwaltet oder Standardwerte zurückgibt.

Nach dem REST-Paradigma, das Sie aus der Vorlesung bereits kennen, sollen obige Methoden über eine entsprechende RESTful-Webservice-API veröffentlicht werden.

Ergänzen Sie die Klasse `StudentService` um alle notwendigen JAX-RS-Annotationen, so dass der *Student Service* über eine RESTful-Webservice-API verfügt.

Die URI einer `Student`-Ressource inkl. dem *Resource-Identifier* (der Primärschlüssel, hier die Matrikelnummer) `981234` sei beispielhaft:

`http://localhost:8081/restapi/studentaffairs/students/981234`

Als Regel für diese Aufgabe gilt, dass der *Resource-Identifier* für alle Methoden außer `POST` Teil der URI sein muss.

Nutzen Sie diese Angaben aus dem obigen Beispiel, um die notwendigen Annotationen entsprechend zu setzen. 

>Der Port `8081` sowie der Kontextpfad `restapi` sind bereits in der Klasse `app.Server` voreingestellt (und könnten dort auch angepasst werden). 
`localhost` kann natürlich jederzeit durch den eigenen *Netzwerk-Hostname* bzw. die *IP-Adresse* ausgetauscht werden, damit der Service auch über das Netzwerk aufrufbar wird.  

Sofern Ihr Projekt über eine funktionierende RESTful-Webservice-API verfügt, testen Sie die Einschränkung der zulässigen Media-Typen (XML und JSON).
Nutzen Sie die Annotationen `@Consumes` und `@Produces`, so dass individuell nur JSON, nur XML oder explizit beide als zulässige Request- und/oder Response-Parameter zulässig sind.

Die Paramter der Methode `getStudentsByRange` sollen als *"Query-Parameter"* `from` und `to` übergeben werden.
Nutzen Sie hierfür die Annotation `@QueryParam`. Der Requestparameter würde dann wie folgt lauten: `/restapi/studentaffairs/students?from=123&to=301` 

## Tests

Testen Sie Ihre Implementierung jeweils mit einem generischen REST-Client (Browser-Plugins, z. B. *RESTER*).