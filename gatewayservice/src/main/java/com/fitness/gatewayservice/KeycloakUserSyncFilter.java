package com.fitness.gatewayservice;

import com.fitness.gatewayservice.api.dto.UserRequest;
import com.fitness.gatewayservice.app.service.UserKeycloakService;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
// @Slf4j
public class KeycloakUserSyncFilter implements WebFilter {
  private final UserKeycloakService userService;

  private static final Logger log = LoggerFactory.getLogger(KeycloakUserSyncFilter.class);

  public KeycloakUserSyncFilter(UserKeycloakService userService) {
    this.userService = userService;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    // ✅ 1. Skip CORS preflight requests
    if ("OPTIONS".equalsIgnoreCase(exchange.getRequest().getMethod().name())) {
      return chain.filter(exchange);
    }
    String token = exchange.getRequest().getHeaders().getFirst("Authorization");
    String userId = exchange.getRequest().getHeaders().getFirst("X-User-ID");

    // ✅ 2. If no token → just continue
    if (token == null || !token.startsWith("Bearer ")) {
      return chain.filter(exchange);
    }
    UserRequest registerRequest = getUserDetails(token);

    if (userId == null) {
      userId = registerRequest.keycloakId();
    }

    if (userId != null && token != null) {
      String finalUserId = userId;
      return userService
          .validateUser(userId)
          .flatMap(
              exist -> {
                if (!exist) {
                  // Register User

                  if (registerRequest != null) {
                    return userService.registerUser(registerRequest).then(Mono.empty());
                  } else {
                    return Mono.empty();
                  }
                } else {
                  log.info("User already exist, Skipping sync.");
                  return Mono.empty();
                }
              })
          .then(
              Mono.defer(
                  () -> {
                    ServerHttpRequest mutatedRequest =
                        exchange.getRequest().mutate().header("X-User-ID", finalUserId).build();
                    return chain.filter(exchange.mutate().request(mutatedRequest).build());
                  }));
    }
    return chain.filter(exchange);
  }

  private UserRequest getUserDetails(String token) {
    try {
      String tokenWithoutBearer = token.replace("Bearer ", "").trim();
      SignedJWT signedJWT = SignedJWT.parse(tokenWithoutBearer);
      JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
      return new UserRequest(
          claims.getStringClaim("email"), // email
          "dummy@123123", // password
          claims.getStringClaim("sub"), // keycloakId
          claims.getStringClaim("given_name"), // firstName
          claims.getStringClaim("family_name") // lastName
          );
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}
