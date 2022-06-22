package de.othr.vs.service;

import io.grpc.stub.StreamObserver;
import your.pkg.Bewertung;
import your.pkg.Messwert;
import your.pkg.MesswertServiceGrpc;
import your.pkg.Void;

public class MesswertServiceImpl extends MesswertServiceGrpc.MesswertServiceImplBase {

    @Override
    public void speichern(Messwert messwert, StreamObserver<Void> callback) {
        System.out.println("Speichere Messwert: " + messwert.getBeschreibung() + " " + messwert.getWert());
        callback.onNext(your.pkg.Void.newBuilder().build());
        callback.onCompleted();
    }

    @Override
    public StreamObserver<Messwert> ueberwachungStarten(StreamObserver<Bewertung> callbackFuerBewertungen) {
        return new UeberwachungsVorgang(callbackFuerBewertungen);
    }
}

class UeberwachungsVorgang implements StreamObserver<Messwert> {

    private StreamObserver<Bewertung> callback;
    private int messwertCounter = 0;

    UeberwachungsVorgang(StreamObserver<Bewertung> callback) {
        this.callback = callback;
    }

    @Override
    public void onNext(Messwert messwert) {
        System.out.println("Messwert empfangen: " + messwert);
        messwertCounter++;
        if(messwertCounter % 3 == 0)
            callback.onNext(your.pkg.Bewertung.newBuilder().setText("Bewertung nach " + messwertCounter + ". Messwert").build());
        if(messwertCounter >= 10)
            callback.onCompleted();
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Fehler beim Empfangen eines Messwerts!");
    }

    @Override
    public void onCompleted() {
        System.out.println("Es kommen keine Messwerte mehr, Client hat beendet");
    }
}