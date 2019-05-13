package com.test.automation;

import com.test.automation.config.PropertiesInitialiser;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.test.automation.driverSetUp")
public class Application {
  public static void main(String[] args) {
    new SpringApplicationBuilder(Application.class)
            .initializers(new PropertiesInitialiser())
            .run(args);
  }
}
