package com.voiture;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "app.data-initializer.enabled=false",
        "eureka.client.enabled=false"
})
class VoitureApplicationTests {

    @Test
    void contextLoads() {
    }
}
