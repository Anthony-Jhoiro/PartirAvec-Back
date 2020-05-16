package com.partiravec.destinationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DestinationserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DestinationserviceApplication.class, args);
    }

}
