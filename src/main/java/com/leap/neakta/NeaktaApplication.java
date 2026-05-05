package com.leap.neakta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling  // ← add this
public class NeaktaApplication {
    public static void main(String[] args) {
        SpringApplication.run(NeaktaApplication.class, args);
    }
}