package com.example.calculator.grpc;

import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class CalculatorServiceImpl extends CalculatorGrpc.CalculatorImplBase {

    @Override
    public void add(BinaryOperationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        System.out.println("gRPC Server performing add(" + request.getNum1() + ", " + request.getNum2() + ")");
        
        double result = request.getNum1() + request.getNum2();
        
        CalculationResponse response = CalculationResponse.newBuilder()
                .setResult(result)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void subtract(BinaryOperationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        System.out.println("gRPC Server performing subtract(" + request.getNum1() + ", " + request.getNum2() + ")");
        
        double result = request.getNum1() - request.getNum2();
        
        CalculationResponse response = CalculationResponse.newBuilder()
                .setResult(result)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void multiply(BinaryOperationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        System.out.println("gRPC Server performing multiply(" + request.getNum1() + ", " + request.getNum2() + ")");
        
        double result = request.getNum1() * request.getNum2();
        
        CalculationResponse response = CalculationResponse.newBuilder()
                .setResult(result)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void divide(BinaryOperationRequest request, StreamObserver<CalculationResponse> responseObserver) {
        System.out.println("gRPC Server performing divide(" + request.getNum1() + ", " + request.getNum2() + ")");
        
        if (request.getNum2() == 0) {
            responseObserver.onError(
                Status.INVALID_ARGUMENT
                    .withDescription("Cannot divide by zero.")
                    .asRuntimeException()
            );
            return;
        }
        
        double result = request.getNum1() / request.getNum2();
        
        CalculationResponse response = CalculationResponse.newBuilder()
                .setResult(result)
                .build();
        
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
