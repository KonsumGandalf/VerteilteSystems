package de.othr.grpc.service;

import bring.me.api.grpc.Invoice;
import bring.me.api.grpc.Order;
import bring.me.api.grpc.OrderServiceGrpc;
import io.grpc.stub.StreamObserver;

public class OrderServiceImpl extends OrderServiceGrpc.OrderServiceImplBase {

    @Override
    public void orderDish(Order order, StreamObserver<Invoice> callback) {
        System.out.println("neue Order: " + order);

        Invoice invoice = Invoice.newBuilder().setSum(999).setVat(70).build();
        callback.onNext(invoice);
        callback.onNext(invoice);
        callback.onCompleted();
    }

    @Override
    public StreamObserver<Order> businessOrder(StreamObserver<Invoice> responseObserver) {
        FoodMaker chef = new FoodMaker();
        return chef;
    }

    static class FoodMaker implements StreamObserver<Order> {
        @Override
        public void onNext(Order order) {
            System.out.println("Cooking the food of: "+order);
        }

        @Override
        public void onError(Throwable throwable) {
            System.out.println("Kitchen is broken: "+throwable.getMessage());
        }

        @Override
        public void onCompleted() {
            System.out.println("Order completed: Transfer to courier");

        }
    }
}
