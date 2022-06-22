package de.othr.grpc;

import de.othr.grpc.service.OrderServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;

public class SampleServer {

    public static final String SAMPLE_GRPC_HOST = "localhost";
    public static final int SAMPLE_GRPC_PORT    = 1234;

    public static void main(String[] args) throws IOException, InterruptedException {
        /* NICHT Prüfungsrelevant */
        Server server = ServerBuilder.forPort(SAMPLE_GRPC_PORT)
                .addService(ProtoReflectionService.newInstance()) // For easier testing with clients like grpcui or grpcurl; remove for production
                .addService(new OrderServiceImpl())
                .build();

        server.start();

        System.out.println("Server running...");

        server.awaitTermination();

    }


}
