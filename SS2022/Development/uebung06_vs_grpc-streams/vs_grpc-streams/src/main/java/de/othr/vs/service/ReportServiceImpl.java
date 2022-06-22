/*
package de.othr.vs.service;
import io.grpc.stub.StreamObserver;
import messung.amt.api.ReportServiceGrpc;
import messung.amt.api.Bericht;
import messung.amt.api.Messwert;
import messung.amt.api.Void;

import java.util.Date;


public class ReportServiceImpl extends ReportServiceGrpc.ReportServiceImplBase {

    @Override
    public void store(Messwert messwert, StreamObserver<Void> callback) {
        System.out.println("Store Messwert: " + messwert);
        callback.onNext(Void.newBuilder().build());
        callback.onCompleted();
    }

    @Override
    public StreamObserver<Messwert> analyseData(StreamObserver<Bericht> callback) {
        return new FachkraftAnalyse(callback);
    }


}

class FachkraftAnalyse implements StreamObserver<Messwert> {
    private StreamObserver<Bericht> callback;
    private int messWertCounter = 0;
    public FachkraftAnalyse(StreamObserver<Bericht> callback) {
        this.callback = callback;
    }

    @Override
    public void onNext(Messwert messwert) {
        System.out.println("Messwert empfangen: " + messwert);
        this.messWertCounter++;
        if(this.messWertCounter % 3 == 0) {
            Bericht bericht = Bericht.newBuilder()
                    .setId(messwert.getId())
                    .setRisk((int) (messwert.getTemperature() / 20))
                    .setUhrzeit(new Date().toString())
                    .setAuthor("David")
                    .setDescription("Dei Hosen hod a Loch")
                    .build();

            callback.onNext(bericht);
        }
        if(this.messWertCounter >= 10)
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
}*/
