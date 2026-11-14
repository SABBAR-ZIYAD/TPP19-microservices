package com.client.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    // L'identifiant est généré automatiquement par la base
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private Float age;
}
