package com.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GateWayApplication {

    @Bean
    RouteLocator routes(RouteLocatorBuilder builder) {
        // Les URI lb:// permettent à la Gateway de trouver les services via Eureka
        return builder.routes()
                // Routes du microservice Client
                .route("service-client", route -> route
                        .path("/clients/**", "/client/**")
                        .uri("lb://SERVICE-CLIENT"))
                // Routes du microservice Voiture
                .route("service-voiture", route -> route
                        .path("/voitures/**")
                        .uri("lb://SERVICE-VOITURE"))
                .build();
    }

    // Démarrage de la Gateway
    public static void main(String[] args) {
        SpringApplication.run(GateWayApplication.class, args);
    }
}
