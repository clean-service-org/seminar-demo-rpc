package com.example.calculator.rpc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application for RPC Calculator Client.
 */
@SpringBootApplication
public class RpcClientApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(RpcClientApplication.class, args);
    }
}
