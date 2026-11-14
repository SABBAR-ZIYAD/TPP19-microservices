package com.tp19;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class Tp19Application {

    // Point d'entrée du serveur Eureka
    public static void main(String[] args) {
        SpringApplication.run(Tp19Application.class, args);
    }
}
