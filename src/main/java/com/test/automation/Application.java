package com.test.automation;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.test.automation.driverSetUp")
public class Application {
  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class)
            .run(args);
  }
}
