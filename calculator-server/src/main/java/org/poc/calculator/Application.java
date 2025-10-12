package org.poc.calculator;

import org.apache.thrift.protocol.TJSONProtocol;
import org.apache.thrift.server.TServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

  @Bean
  public TServlet calculatorServlet() {
    // Create the handler and processor
    CalculatorHandler handler = new CalculatorHandler();
    Calculator.Processor<CalculatorHandler> processor = new Calculator.Processor<>(handler);

    // Create the servlet that speaks the Thrift protocol over HTTP.
    // We use TJSONProtocol to make it easy to debug.
    return new TServlet(processor, new TJSONProtocol.Factory());
  }

  @Bean
  public ServletRegistrationBean<TServlet> servletRegistrationBean(TServlet servlet) {
    // Map the Thrift servlet to the /calculator URL endpoint
    return new ServletRegistrationBean<>(servlet, "/calculator");
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
    System.out.println("Thrift server started on http://localhost:8080/calculator");
  }
}
