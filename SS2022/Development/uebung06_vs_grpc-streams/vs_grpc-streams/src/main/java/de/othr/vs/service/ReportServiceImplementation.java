package de.othr.vs.service;

import com.google.protobuf.AnyOrBuilder;
import de.othr.vs.server.ReportServer;
import io.grpc.stub.StreamObserver;
import messung.amt.api.ReportServiceGrpc;
import messung.amt.api.Bericht;
import messung.amt.api.Messwert;
import messung.amt.api.Void;

import java.util.Date;
import java.util.Random;


public class ReportServiceImplementation extends ReportServiceGrpc.ReportServiceImplBase {

    @Override
    public StreamObserver<Messwert> analyseData(StreamObserver<Bericht> callback) {
        return new AnalyserClass(callback);
    }
}

class AnalyserClass implements StreamObserver<Messwert> {
    static Random random = new Random();
    private int messWertCounter = 0;
    private StreamObserver<Bericht> callback;
    AnalyserClass(StreamObserver<Bericht> callback){
        this.callback = callback;
    }

    @Override
    public void onNext(Messwert messwert) {
        ++this.messWertCounter;
        System.out.println("Report received {" + messwert + "}");
        int r1 = random.nextInt(5);
        int risk;
        if (messwert.getTemperature() > 10 ) {
            risk = r1 + 5;
        } else {
            risk = r1;
        }
        Bericht bericht = Bericht.newBuilder()
                .setId(messwert.getId())
                .setRisk(risk)
                .setUhrzeit(new Date().toString())
                .setAuthor("David")
                .setDescription("Dei Hosen hod a Loch")
                .build();

        callback.onNext(bericht);

        if(this.messWertCounter >= 9)callback.onCompleted();
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
