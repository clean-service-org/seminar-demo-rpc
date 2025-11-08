package com.example.calculator.rpc.config;

import com.example.calculator.rpc.Calculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Configuration for RPC client connection.
 */
@Configuration
public class RpcConfig {
    
    private static final String SERVER_HOST = "localhost";
    private static final int RPC_PORT = 1099;
    private static final String SERVICE_NAME = "CalculatorService";
    
    @Bean
    public Calculator calculatorRpcClient() throws Exception {
        try {
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, RPC_PORT);
            Calculator calculator = (Calculator) registry.lookup(SERVICE_NAME);
            System.out.println("✓ Connected to RPC Calculator Service at " + SERVER_HOST + ":" + RPC_PORT);
            return calculator;
        } catch (Exception e) {
            System.err.println("✗ Failed to connect to RPC server: " + e.getMessage());
            throw new RuntimeException("Cannot connect to RPC server", e);
        }
    }
}
