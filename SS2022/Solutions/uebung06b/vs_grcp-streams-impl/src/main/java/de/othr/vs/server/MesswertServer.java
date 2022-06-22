package de.othr.vs.server;

import de.othr.vs.service.MesswertServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class MesswertServer {

    public static final String SERVER_GRPC_HOST = "localhost";
    public static final int SERVER_GRPC_PORT    = 1234;

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(SERVER_GRPC_PORT)
                .addService(new MesswertServiceImpl())
                .build();

        server.start();

        System.out.println("Server running...");

        server.awaitTermination();
    }
}
