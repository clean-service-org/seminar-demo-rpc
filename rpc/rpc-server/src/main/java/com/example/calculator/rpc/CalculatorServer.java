package com.example.calculator.rpc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Spring Boot RMI Server for the Calculator service.
 * Creates and registers the Calculator implementation with the RMI registry.
 */
@SpringBootApplication
public class CalculatorServer implements CommandLineRunner {
    
    public static final int RMI_PORT = 1099;  // Default RMI registry port
    public static final String SERVICE_NAME = "CalculatorService";
    
    public static void main(String[] args) {
        SpringApplication.run(CalculatorServer.class, args);
    }
    
    @Override
    public void run(String... args) throws Exception {
        // Create the calculator implementation
        CalculatorImpl calculator = new CalculatorImpl();
        
        // Create and export the RMI registry on the default port
        Registry registry = LocateRegistry.createRegistry(RMI_PORT);
        
        // Bind the remote object in the registry
        registry.rebind(SERVICE_NAME, calculator);
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   RMI Calculator Server Started        ║");
        System.out.println("╚════════════════════════════════════════╝");
        System.out.println();
        System.out.println("Service Name: " + SERVICE_NAME);
        System.out.println("RMI Port:     " + RMI_PORT);
        System.out.println();
        System.out.println("Server is ready to accept requests...");
        System.out.println("Press Ctrl+C to stop the server");
    }
}
