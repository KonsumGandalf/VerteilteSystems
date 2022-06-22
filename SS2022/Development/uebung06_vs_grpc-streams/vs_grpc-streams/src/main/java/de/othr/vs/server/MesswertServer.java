/*
package de.othr.vs.server;

import de.othr.vs.service.ReportServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import messung.amt.api.ReportServiceGrpc;

import java.io.IOException;

public class MesswertServer {

    public static final String SERVER_GRPC_HOST = "localhost";
    public static final int SERVER_GRPC_PORT    = 1234;

    public static void main(String[] args) throws IOException, InterruptedException {

        Server server = ServerBuilder.forPort(SERVER_GRPC_PORT)
                // .addService(ReportServiceGrpc.ReportServiceStub.newInstance()) // For easier testing with clients like grpcui or grpcurl; remove for production
                .addService(new ReportServiceImpl())
                .build();

        server.start();

        System.out.println("Server running...");

        server.awaitTermination();

    }
}
*/
