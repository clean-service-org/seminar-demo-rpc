package com.example.calculator.rpc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Servlet controller for the RPC Calculator web client.
 * Handles calculator operations by calling the remote RPC service.
 */
@WebServlet(name = "CalculatorServlet", urlPatterns = {"/calculate", "/"})
public class CalculatorServlet extends HttpServlet {
    
    private static final String SERVER_HOST = "localhost";
    private static final int RPC_PORT = 1099;
    private static final String SERVICE_NAME = "CalculatorService";
    
    private Calculator calculator;
    private TemplateEngine templateEngine;
    
    @Override
    public void init() throws ServletException {
        try {
            // Connect to RPC server on startup
            Registry registry = LocateRegistry.getRegistry(SERVER_HOST, RPC_PORT);
            calculator = (Calculator) registry.lookup(SERVICE_NAME);
            System.out.println("✓ Connected to RPC Calculator Service at " + SERVER_HOST + ":" + RPC_PORT);
        } catch (Exception e) {
            System.err.println("✗ Failed to connect to RPC server: " + e.getMessage());
            throw new ServletException("Cannot connect to RPC server", e);
        }
        
        // Initialize Thymeleaf
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
        
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));
        templateResolver.setCacheable(true);
        
        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        WebContext context = new WebContext(request, response, getServletContext());
        
        templateEngine.process("calculator", context, response.getWriter());
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        
        WebContext context = new WebContext(request, response, getServletContext());
        
        try {
            // Parse input parameters
            double num1 = Double.parseDouble(request.getParameter("num1"));
            double num2 = Double.parseDouble(request.getParameter("num2"));
            String operation = request.getParameter("operation");
            
            double result = 0;
            String error = null;
            
            // Perform the RPC call based on operation
            try {
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
                        error = "Invalid operation";
                }
            } catch (ArithmeticException e) {
                error = e.getMessage();
            } catch (Exception e) {
                error = "RPC call failed: " + e.getMessage();
            }
            
            // Set variables for Thymeleaf
            context.setVariable("num1", num1);
            context.setVariable("num2", num2);
            context.setVariable("operation", operation);
            if (error != null) {
                context.setVariable("error", error);
            } else {
                context.setVariable("result", result);
            }
            
        } catch (NumberFormatException e) {
            context.setVariable("error", "Invalid number format");
        }
        
        // Render the template
        templateEngine.process("calculator", context, response.getWriter());
    }
}
