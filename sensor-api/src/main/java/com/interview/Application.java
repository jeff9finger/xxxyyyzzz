package com.interview;

import com.fasterxml.jackson.databind.Module;
import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 */

@SpringBootApplication(scanBasePackages = "com.interview")
public class Application {
    public static void main(String[] args) {
     SpringApplication.run(Application.class, args);
    }

    @Bean(name = "com.interview.Application.jsonNullableModule")
    public Module jsonNullableModule() {
        return new JsonNullableModule();
    }
}
