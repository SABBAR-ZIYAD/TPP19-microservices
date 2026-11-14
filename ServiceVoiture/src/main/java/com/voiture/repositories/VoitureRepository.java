package com.voiture.repositories;

import com.voiture.entities.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

    // Recherche toutes les voitures appartenant à un client
    List<Voiture> findByClientId(Long clientId);
}
