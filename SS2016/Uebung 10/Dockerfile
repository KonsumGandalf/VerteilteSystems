# Image wird auf Basis des Images java:8 aufgebaut
FROM java:8
# Details ueber den Nutzer (optional)
MAINTAINER Max Muster max.muster@stud.oth-regensburg.de
# RUN fuehrt einen Linux-Befehl innerhalb des Images aus
RUN mkdir /usr/src/vs
# WORKDIR wechselt das Arbeitsverzeichnis innerhalb des Images auf den angegebenen Pfad
WORKDIR /usr/src/vs
# COPY von ausserhalb des Images in das Image
COPY uebung10.jar /usr/src/vs/uebung10.jar
# Angabe, welche Ports ein laufender Container nach Aussen geben darf
EXPOSE 8080 5701
# Befehl, der ausgefuehrt wird, sobald einer neuer Container mit "run" gestartet wird
CMD java -jar uebung10.jar
