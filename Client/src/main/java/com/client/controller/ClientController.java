package com.client.controller;

import com.client.entity.Client;
import com.client.repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@RestController
public class ClientController {
    @Autowired
    ClientRepository clientRepository;

    // Retourne la liste de tous les clients
    @GetMapping("/clients")
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    // Les deux chemins sont gardés pour les appels directs et Feign
    @GetMapping({"/client/{id}", "/clients/{id}"})
    public Client findById(@PathVariable Long id) throws Exception {
        return clientRepository.findById(id)
                .orElseThrow(() -> new Exception("Client non trouvé"));
    }
}
