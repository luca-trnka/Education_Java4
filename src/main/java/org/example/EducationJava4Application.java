package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "org.example")
public class EducationJava4Application {

    public static void main(String[] args) {
        SpringApplication.run(EducationJava4Application.class, args);
    }
}