package com.javaschool.cianparser;


import com.javaschool.cianparser.services.parsers.CianExtractorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableEurekaClient
public class CianParserApplication {
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication.run(CianParserApplication.class, args);
    }
}

