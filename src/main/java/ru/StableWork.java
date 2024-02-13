package ru;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class StableWork {

    public static void main(String[] args) {
        SpringApplication.run(StableWork.class, args);
    }

}