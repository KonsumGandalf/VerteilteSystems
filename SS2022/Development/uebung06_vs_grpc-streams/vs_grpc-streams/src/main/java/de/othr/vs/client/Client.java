/*
package de.othr.vs.client;

import de.othr.vs.server.MesswertServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import messung.amt.api.Messwert;
import messung.amt.api.Bericht;
import messung.amt.api.ReportServiceGrpc;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(MesswertServer.SERVER_GRPC_HOST,
                        MesswertServer.SERVER_GRPC_PORT)
                .usePlaintext()
                .build();

        // Stub generieren (je nach Anwendungsfall stub, blocking stub oder future stub)

        // Messages generieren und Service aufrufen

        // Teilaufgabe 1
        ReportServiceGrpc.ReportServiceStub stub = ReportServiceGrpc.newStub(channel);
        StreamObserver<Messwert> messwertStreamObserver = stub.analyseData(new FachkraftMessen());

        for(int i = 0; i<10; i++){
            messwertStreamObserver.onNext(Messwert.newBuilder()
                    .setId(i)
                    .setTemperature(100)
                    .setUhrzeit("2021-12-01 04:18:53")
                    .setAuthor("Felix")
                    .setOrt("Regensburg")
                    .build());
        }

        channel.awaitTermination(10, TimeUnit.SECONDS);
        */
/* Teilaufgabe 2
        FachkraftMessen fm = new FachkraftMessen();
        ReportServiceGrpc.ReportServiceStub stub =
                ReportServiceGrpc.newStub(channel);
        stub.sendMesswert(messwert, fm);
        channel.awaitTermination(5, TimeUnit.SECONDS);
        *//*


    }


}

class FachkraftMessen implements StreamObserver<Bericht> {
    @Override
    public void onNext(Bericht bericht) {
        System.out.println("Bericht: " + bericht);System.out.println("SSIO");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Transmission error: "+throwable.getMessage());
    }

    @Override
    public void onCompleted() {
        System.out.println("Report completed");

    }
}*/
