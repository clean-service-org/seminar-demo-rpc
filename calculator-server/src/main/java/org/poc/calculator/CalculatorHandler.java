package org.poc.calculator;

public class CalculatorHandler implements Calculator.Iface {

  @Override
  public double add(double num1, double num2) {
    System.out.println("Server performing add(" + num1 + ", " + num2 + ")");
    return num1 + num2;
  }

  @Override
  public double subtract(double num1, double num2) {
    System.out.println("Server performing subtract(" + num1 + ", " + num2 + ")");
    return num1 - num2;
  }

  @Override
  public double multiply(double num1, double num2) {
    System.out.println("Server performing multiply(" + num1 + ", " + num2 + ")");
    return num1 * num2;
  }

  @Override
  public double divide(double num1, double num2) throws CalculationException {
    System.out.println("Server performing divide(" + num1 + ", " + num2 + ")");
    if (num2 == 0) {
      throw new CalculationException("Cannot divide by zero.");
    }
    return num1 / num2;
  }
}
