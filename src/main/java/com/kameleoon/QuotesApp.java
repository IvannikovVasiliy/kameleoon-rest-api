package com.kameleoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class QuotesApp {

    public static void main(String[] args) {
        SpringApplication.run(QuotesApp.class, args);
    }
}
