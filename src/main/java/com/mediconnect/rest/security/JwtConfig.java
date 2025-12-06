package com.mediconnect.rest.security;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Configuration JWT
 * Chemin: src/main/java/com/mediconnect/rest/security/JwtConfig.java
 */
@Component
@Getter
public class JwtConfig {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;
}