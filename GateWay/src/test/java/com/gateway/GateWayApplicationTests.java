package com.gateway;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteLocator;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GateWayApplicationTests {

    @Autowired
    private RouteLocator routeLocator;

    @Test
    void routesMicroservicesThroughEurekaServiceNames() {
        assertThat(routeLocator.getRoutes().collectList().block())
                .extracting(route -> route.getId() + "=" + route.getUri())
                .contains(
                        "service-client=lb://SERVICE-CLIENT",
                        "service-voiture=lb://SERVICE-VOITURE");
    }
}
