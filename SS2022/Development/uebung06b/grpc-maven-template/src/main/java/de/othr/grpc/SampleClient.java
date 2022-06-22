package de.othr.grpc;

import de.othr.grpc.service.OrderServiceImpl;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import bring.me.api.grpc.Order;
import bring.me.api.grpc.OrderServiceGrpc;
import bring.me.api.grpc.Invoice;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.TimeUnit;

public class SampleClient {

    public static void main(String[] args) throws InterruptedException {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress(SampleServer.SAMPLE_GRPC_HOST,
                        SampleServer.SAMPLE_GRPC_PORT)
                .usePlaintext() // plaintext ohne Verschlüsselung zu Testzwecken
                .build();

        Order order = Order.newBuilder()
                .setDish("Big Mac")
                .setAddress("Galgenbergstr.32, K002, Rgbg")
                .setCreditCardNo("6969 1234 8821 2144")
                .build();

        InvoiceTaker bookKeeping = new InvoiceTaker();

        OrderServiceGrpc.OrderServiceStub stub =
                OrderServiceGrpc.newStub(channel);
        //SampleServiceGrpc.SampleServiceBlockingStub stub = SampleServiceGrpc.newBlockingStub(channel);
        stub.orderDish(order, bookKeeping);

        // soll offenbleiben sonst kann Callback nicht bedient werden
        channel.awaitTermination(5, TimeUnit.SECONDS);

        //Generate messages, use stub

    }

    static class InvoiceTaker implements StreamObserver<Invoice> {
        @Override
        public void onNext(Invoice invoice) {
            System.out.println("Dish respectively Invoice arrived: "+invoice);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("Dish wont arrive arrived: "+throwable.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("Order completed");

        }
    }
}
