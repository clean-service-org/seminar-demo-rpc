package com.example.calculator.rpc;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Implementation of the Calculator remote interface.
 * This class extends UnicastRemoteObject to enable RMI functionality.
 */
public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    
    /**
     * Constructor for CalculatorImpl.
     * Must declare RemoteException as required by UnicastRemoteObject.
     * @throws RemoteException if remote object creation fails
     */
    public CalculatorImpl() throws RemoteException {
        super();
    }
    
    @Override
    public double add(double num1, double num2) throws RemoteException {
        double result = num1 + num2;
        System.out.println(String.format("Add: %.2f + %.2f = %.2f", num1, num2, result));
        return result;
    }
    
    @Override
    public double subtract(double num1, double num2) throws RemoteException {
        double result = num1 - num2;
        System.out.println(String.format("Subtract: %.2f - %.2f = %.2f", num1, num2, result));
        return result;
    }
    
    @Override
    public double multiply(double num1, double num2) throws RemoteException {
        double result = num1 * num2;
        System.out.println(String.format("Multiply: %.2f × %.2f = %.2f", num1, num2, result));
        return result;
    }
    
    @Override
    public double divide(double num1, double num2) throws RemoteException, ArithmeticException {
        if (num2 == 0) {
            throw new ArithmeticException("Division by zero is not allowed");
        }
        double result = num1 / num2;
        System.out.println(String.format("Divide: %.2f ÷ %.2f = %.2f", num1, num2, result));
        return result;
    }
}
