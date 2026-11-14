package com.voiture;

import com.voiture.clients.ClientService;
import com.voiture.entities.Client;
import com.voiture.entities.Voiture;
import com.voiture.repositories.VoitureRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@EnableFeignClients
@SpringBootApplication
public class VoitureApplication {

    // Démarrage du microservice voiture
    public static void main(String[] args) {
        SpringApplication.run(VoitureApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(name = "app.data-initializer.enabled", matchIfMissing = true)
    CommandLineRunner initialiserBaseH2(
            VoitureRepository voitureRepository,
            ClientService clientService) {
        return args -> {
            Client c1 = null;
            Client c2 = null;

            // On essaie de récupérer les clients avec Feign. Si SERVICE-CLIENT
            // n'est pas encore démarré, cela ne doit pas bloquer ce service.
            try {
                c1 = clientService.clientById(2L);
                c2 = clientService.clientById(1L);
            } catch (Exception exception) {
                System.out.println("SERVICE-CLIENT n'est pas encore disponible. "
                        + "Les voitures seront quand même initialisées.");
            }

            // Ajout de quelques voitures dans la base H2
            voitureRepository.save(new Voiture(null, "Toyota", "A 25 333", "Corolla", 1L, c2));
            voitureRepository.save(new Voiture(null, "Renault", "B 6 3456", "Megane", 1L, c2));
            voitureRepository.save(new Voiture(null, "Peugeot", "A 55 4444", "301", 2L, c1));
        };
    }
}
