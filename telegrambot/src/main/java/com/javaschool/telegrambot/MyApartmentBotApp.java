package com.javaschool.telegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class MyApartmentBotApp {
    public static void main(String[] args) {
        SpringApplication.run(MyApartmentBotApp.class, args);
    }
}
