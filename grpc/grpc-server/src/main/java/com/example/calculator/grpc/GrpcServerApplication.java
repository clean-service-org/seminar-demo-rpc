package com.example.calculator.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class GrpcServerApplication implements CommandLineRunner {

    private Server server;
    private static final int PORT = 9090;

    public static void main(String[] args) {
        SpringApplication.run(GrpcServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        startServer();
        blockUntilShutdown();
    }

    private void startServer() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService(new CalculatorServiceImpl())
                .build()
                .start();
        
        System.out.println("gRPC server started on port " + PORT);
        
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server...");
            stopServer();
            System.err.println("gRPC server shut down.");
        }));
    }

    private void stopServer() {
        if (server != null) {
            server.shutdown();
        }
    }

    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
