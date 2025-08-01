package org.learningspringwithduc.gatewayservice.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements GatewayFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {


        ServerHttpRequest request = exchange.getRequest();

        String authHeader = request.getHeaders().getFirst("Authorization");

        ServerHttpRequest.Builder mutatedRequest = request.mutate();

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String token = authHeader.substring(7);

                System.out.println("Token: " + token);

                String userIdStr = jwtUtil.extractUserId(token);
                System.out.println("userIdStr: " + userIdStr);

                mutatedRequest.header("X-User-Id", userIdStr);

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
                mutatedRequest.header("X-User-Id", "");
            }
        }
        else {

            mutatedRequest.header("X-User-Id", "");
        }

        return chain.filter(exchange.mutate().request(mutatedRequest.build()).build());
    }
}
