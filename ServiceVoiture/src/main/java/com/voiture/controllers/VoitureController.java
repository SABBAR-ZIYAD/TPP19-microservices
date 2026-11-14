package com.voiture.controllers;

import com.voiture.clients.ClientService;
import com.voiture.entities.Client;
import com.voiture.entities.Voiture;
import com.voiture.repositories.VoitureRepository;
import com.voiture.services.VoitureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VoitureController {

    private final VoitureRepository voitureRepository;
    private final VoitureService voitureService;
    private final ClientService clientService;

    public VoitureController(
            VoitureRepository voitureRepository,
            VoitureService voitureService,
            ClientService clientService) {
        this.voitureRepository = voitureRepository;
        this.voitureService = voitureService;
        this.clientService = clientService;
    }

    // Retourne les voitures avec les informations de leurs clients
    @GetMapping(value = "/voitures", produces = "application/json")
    public ResponseEntity<List<Voiture>> findAll() {
        List<Voiture> voitures = voitureRepository.findAll();
        voitures.forEach(this::attachClient);
        return ResponseEntity.ok(voitures);
    }

    // Recherche une voiture par son identifiant
    @GetMapping("/voitures/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        return voitureRepository.findById(id)
                .<ResponseEntity<Object>>map(voiture -> {
                    attachClient(voiture);
                    return ResponseEntity.ok(voiture);
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Voiture not found with ID: " + id));
    }

    // Retourne toutes les voitures d'un client
    @GetMapping("/voitures/client/{id}")
    public ResponseEntity<List<Voiture>> findByClient(@PathVariable Long id) {
        Client client = clientService.clientById(id);
        List<Voiture> voitures = voitureRepository.findByClientId(id);
        voitures.forEach(voiture -> voiture.setClient(client));
        return ResponseEntity.ok(voitures);
    }

    // Crée une voiture et l'associe au client indiqué dans l'URL
    @PostMapping("/voitures/{clientId}")
    public ResponseEntity<Voiture> save(
            @PathVariable Long clientId,
            @RequestBody Voiture voiture) {
        Client client = clientService.clientById(clientId);
        voiture.setId(null);
        voiture.setClientId(clientId);
        voiture.setClient(client);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(voitureService.enregistrerVoiture(voiture));
    }

    // Modifie uniquement les champs fournis dans la requête
    @PutMapping("/voitures/{id}")
    public ResponseEntity<Object> update(
            @PathVariable Long id,
            @RequestBody Voiture update) {
        return voitureRepository.findById(id)
                .<ResponseEntity<Object>>map(existing -> {
                    if (hasText(update.getMatricule())) {
                        existing.setMatricule(update.getMatricule());
                    }
                    if (hasText(update.getMarque())) {
                        existing.setMarque(update.getMarque());
                    }
                    if (hasText(update.getModel())) {
                        existing.setModel(update.getModel());
                    }
                    return ResponseEntity.ok(voitureRepository.save(existing));
                })
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Voiture not found with ID: " + id));
    }

    // Complète une voiture avec le client récupéré par Feign
    private void attachClient(Voiture voiture) {
        voiture.setClient(clientService.clientById(voiture.getClientId()));
    }

    private boolean hasText(String value) {
        return value != null && !value.isBlank();
    }
}
