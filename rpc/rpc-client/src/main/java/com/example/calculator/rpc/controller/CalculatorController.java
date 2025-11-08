package com.example.calculator.rpc.controller;

import com.example.calculator.rpc.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Spring MVC controller for the RPC Calculator web client.
 */
@Controller
public class CalculatorController {
    
    private final Calculator calculator;
    
    @Autowired
    public CalculatorController(Calculator calculator) {
        this.calculator = calculator;
    }
    
    @GetMapping("/")
    public String showCalculator() {
        return "calculator";
    }
    
    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("num1") double num1,
            @RequestParam("num2") double num2,
            @RequestParam("operation") String operation,
            Model model) {
        
        try {
            double result = 0;
            
            switch (operation) {
                case "add":
                    result = calculator.add(num1, num2);
                    break;
                case "subtract":
                    result = calculator.subtract(num1, num2);
                    break;
                case "multiply":
                    result = calculator.multiply(num1, num2);
                    break;
                case "divide":
                    result = calculator.divide(num1, num2);
                    break;
                default:
                    model.addAttribute("error", "Invalid operation");
                    return addFormData(num1, num2, operation, model);
            }
            
            model.addAttribute("result", result);
            
        } catch (ArithmeticException e) {
            model.addAttribute("error", e.getMessage());
        } catch (Exception e) {
            model.addAttribute("error", "RPC call failed: " + e.getMessage());
        }
        
        return addFormData(num1, num2, operation, model);
    }
    
    private String addFormData(double num1, double num2, String operation, Model model) {
        model.addAttribute("num1", num1);
        model.addAttribute("num2", num2);
        model.addAttribute("operation", operation);
        return "calculator";
    }
}
