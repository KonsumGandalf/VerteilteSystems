package de.othr.vs.server;

import de.othr.vs.service.ReportServiceImplementation;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ReportServer {
    public static String SERVER_GRPC_HOST = "localhost";
    public static int SERVER_GRPC_PORT = 1871;


    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(SERVER_GRPC_PORT)
                .addService(new ReportServiceImplementation())
                .build();

        server.start();
        server.awaitTermination();
    }
}
