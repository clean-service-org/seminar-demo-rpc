namespace java com.example.calculator
namespace js calculator

exception CalculationException {
  1: string message
}

service Calculator {
  double add(1:double num1, 2:double num2),
  double subtract(1:double num1, 2:double num2),
  double multiply(1:double num1, 2:double num2),
  double divide(1:double num1, 2:double num2) throws (1:CalculationException ex)
}