package de.othr.vs.client;

import com.google.protobuf.Message;
import de.othr.vs.server.MesswertServer;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import your.pkg.Bewertung;
import your.pkg.MesswertServiceGrpc;
import your.pkg.Void;

import java.util.concurrent.TimeUnit;

public class Client {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(MesswertServer.SERVER_GRPC_HOST,
                        MesswertServer.SERVER_GRPC_PORT)
                .usePlaintext()
                .build();

        // Stub generieren (je nach Anwendungsfall stub, blocking stub oder future stub)
//        MesswertServiceGrpc.MesswertServiceBlockingStub blockingStub = MesswertServiceGrpc.newBlockingStub(channel);
        MesswertServiceGrpc.MesswertServiceStub stub = MesswertServiceGrpc.newStub(channel);

        your.pkg.Messwert messwert = your.pkg.Messwert.newBuilder()
                .setWert(3.20)
                .setBeschreibung("Wasserstand")
                .build();
        StreamObserver<your.pkg.Messwert> messageStreamObserver = stub.ueberwachungStarten(new CallbackImpl());

        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);
        messageStreamObserver.onNext(messwert);




        // Client noch nicht beenden, Callbacks vom Server (via StreamObserver::onNext, ...)
        // werden sonst nicht mehr empfangen
        channel.awaitTermination(30L, TimeUnit.SECONDS);
    }
}

class CallbackImpl implements StreamObserver<your.pkg.Bewertung> {

    @Override
    public void onNext(Bewertung bewertung) {
        System.out.println("Bewertung erhalten: " + bewertung.getText() + " " + bewertung.getTimestamp());
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Fehler beim Erhalt einer Bewertung");
    }

    @Override
    public void onCompleted() {
        System.out.println("Bewertungen (Überwachung) abgeschlossen");
    }
}
