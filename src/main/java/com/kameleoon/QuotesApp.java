package com.kameleoon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//tansactional
// почистить репозитории
public class QuotesApp {

    public static void main(String[] args) {
        SpringApplication.run(QuotesApp.class, args);
    }
}
