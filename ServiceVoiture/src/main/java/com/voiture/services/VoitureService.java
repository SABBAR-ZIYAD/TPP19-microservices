package com.voiture.services;

import com.voiture.entities.Voiture;
import com.voiture.repositories.VoitureRepository;
import org.springframework.stereotype.Service;

@Service
public class VoitureService {

    private final VoitureRepository voitureRepository;

    public VoitureService(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    // Centralise l'enregistrement d'une voiture
    public Voiture enregistrerVoiture(Voiture voiture) {
        return voitureRepository.save(voiture);
    }
}
