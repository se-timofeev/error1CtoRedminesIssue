package ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StartApp {

    public static void main(String[] args) {

        SpringApplication.run(StartApp.class, args);
    }

}