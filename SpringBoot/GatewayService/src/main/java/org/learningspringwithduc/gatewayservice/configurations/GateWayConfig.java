package org.learningspringwithduc.gatewayservice.configurations;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

public class GateWayConfig {

    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("login-service", r -> r.path("/login/**")
                        .uri("https://localhost:8082"))
                .route("composite-service", r -> r.path("/composite/**")
                        .uri("https://localhost:8083"))
                .build();
    }
}
