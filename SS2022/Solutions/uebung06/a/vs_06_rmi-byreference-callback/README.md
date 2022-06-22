# Übungsblatt 6

## Java-RMI-Praxisszenairen: Übergabe By-Reference und Request/Callback

Der Quellcode aus Übungsaufgabe 5 bildet die Basis für die folgenden Aufgaben.
Erstellen Sie zu Beginn jede der folgenden drei Teilaufgaben eine eigene Kopie auf der bisherigen Code-Basis („by value“)! Am Ende dieses Übungsblatts sollten Sie vier RMI- Projekte haben, die jeweils immer nur einen Teilaspekt umsetzen:
1. „By value“-Implementierung aus Übungsaufgabe 5 
2. Röntgenbild „by reference“
3. Bericht „by reference“
4. Request/Callback für die Methode „analysiere“

### Teilaufgabe 1
Objekte des Typs Röntgenbild sollen nicht mehr wie bisher „by value“ sondern „by reference“ übergeben werden. Nehmen Sie alle hierzu notwendigen Änderungen vor.
Wie müsste das UML-Diagramm hierzu aussehen bzw. abgeändert werden?

### Teilaufgabe 2
Objekte des Typs Bericht sollen nicht mehr wie bisher „by value“ sondern „by reference“ übergeben werden. Nehmen Sie alle hierzu notwendigen Änderungen vor.
Wie müsste das UML-Diagramm hierzu aussehen bzw. abgeändert werden?

### Teilaufgabe 3
Die Interaktionssemantik der Methode analysiere soll auf Request/Callback umgestellt werden. Das heißt, der Server wird nach Ende der Analyse den Bericht über ein vom Client eigens implementiertes Callback-Interface zu stellen, so dass der Client auch bei lang dauernder Analyse die Ablaufkontrolle sofort zurückerhält.
Implementieren Sie diese Änderung. Simulieren Sie einen lang andauernden Analysevorgang auf Serverseite durch Thread.sleep und lagern Sie dies in eine eigenes Runnable aus.
Wie müsste das UML-Diagramm hierzu aussehen bzw. abgeändert werden?

### Zusatzfragen
1. Wäre es möglich, „Request/Callback“ mit „call by reference“ für den Bericht zu kombinieren? Falls ja, wann macht das Sinn? Falls nein, warum geht das nicht?
2. Erklären Sie stichpunktartig, was Sie wie implementieren müssten, um den Aufruf des FrüherkennungServer nach der Interaktionssemantik „Request/Future“ umzusetzen (keine Implementierung).

