package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example")
public class EducationJava4Application {

    private static final Logger log = LoggerFactory.getLogger(EducationJava4Application.class);
    public static void main(String[] args) {
        SpringApplication.run(EducationJava4Application.class, args);
        log.info("Application started");
    }
}