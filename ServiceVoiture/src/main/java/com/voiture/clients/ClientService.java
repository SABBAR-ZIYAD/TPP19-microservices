package com.voiture.clients;

import com.voiture.entities.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "SERVICE-CLIENT")
// Client Feign utilisé pour communiquer avec le microservice Client
public interface ClientService {

    @GetMapping("/clients/{id}")
    Client clientById(@PathVariable("id") Long id);
}
