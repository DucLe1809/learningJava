package org.learningspringwithduc.gatewayservice.configurations;

import org.learningspringwithduc.gatewayservice.jwt.JwtAuthenticationFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig {
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder, JwtAuthenticationFilter jwtAuthenticationFilter) {
        return builder.routes()
                .route("composite-service", r -> r.path("/composite/**")
                        .filters(f -> f.filter(jwtAuthenticationFilter))
                        .uri("http://localhost:8083"))
                .build();
    }
}
