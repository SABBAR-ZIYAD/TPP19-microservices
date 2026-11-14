package com.voiture.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// Représentation locale d'un client reçu depuis SERVICE-CLIENT
public class Client {
    private Long id;
    private String nom;
    private Float age;
}
