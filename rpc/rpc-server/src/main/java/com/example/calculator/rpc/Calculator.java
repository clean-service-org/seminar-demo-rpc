package com.example.calculator.rpc;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the Calculator service.
 * Defines the methods that can be called remotely via RMI.
 */
public interface Calculator extends Remote {
    
    /**
     * Adds two numbers.
     * @param num1 First number
     * @param num2 Second number
     * @return Sum of num1 and num2
     * @throws RemoteException if remote communication fails
     */
    double add(double num1, double num2) throws RemoteException;
    
    /**
     * Subtracts num2 from num1.
     * @param num1 First number
     * @param num2 Second number
     * @return Difference (num1 - num2)
     * @throws RemoteException if remote communication fails
     */
    double subtract(double num1, double num2) throws RemoteException;
    
    /**
     * Multiplies two numbers.
     * @param num1 First number
     * @param num2 Second number
     * @return Product of num1 and num2
     * @throws RemoteException if remote communication fails
     */
    double multiply(double num1, double num2) throws RemoteException;
    
    /**
     * Divides num1 by num2.
     * @param num1 Numerator
     * @param num2 Denominator
     * @return Quotient (num1 / num2)
     * @throws RemoteException if remote communication fails
     * @throws ArithmeticException if num2 is zero
     */
    double divide(double num1, double num2) throws RemoteException, ArithmeticException;
}
