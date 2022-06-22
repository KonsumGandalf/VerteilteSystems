package de.othr.vs.client;

import de.othr.vs.server.ReportServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import messung.amt.api.Messwert;
import messung.amt.api.Bericht;
import messung.amt.api.ReportServiceGrpc;

import java.util.Date;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Client2 {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(ReportServer.SERVER_GRPC_HOST,
                        ReportServer.SERVER_GRPC_PORT)
                .usePlaintext()
                .build();

        // Stub generieren (je nach Anwendungsfall stub, blocking stub oder future stub)

        // Messages generieren und Service aufrufen

        // Teilaufgabe 1
        ReportServiceGrpc.ReportServiceStub stub = ReportServiceGrpc.newStub(channel);
        StreamObserver<Messwert> messwertStreamObserver = stub.analyseData(new CallbackImpl());

        Random ran = new Random();

        for (int i = 0; i < 10; i++) {
            messwertStreamObserver.onNext(Messwert.newBuilder()
                    .setId(i+1)
                    .setTemperature(ran.nextInt(20)+1)
                    .setUhrzeit("2021-12-01 04:18:53")
                    .setAuthor(new String[]{"Felix", "David"}[ran.nextInt(1)])
                    .setOrt("Regensburg")
                    .build());
        }
        channel.awaitTermination(10, TimeUnit.SECONDS);

    }
}

class CallbackImpl implements StreamObserver<Bericht> {

    @Override
    public void onNext(Bericht bericht) {
        System.out.println("The Report has been : {" + bericht + "}");
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Error has occurred " + throwable.toString());
    }

    @Override
    public void onCompleted() {
        System.out.println("Task completed by" + new Date());
    }
}