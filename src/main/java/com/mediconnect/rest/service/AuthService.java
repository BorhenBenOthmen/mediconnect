package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.LoginRequest;
import com.mediconnect.rest.dto.LoginResponse;
import com.mediconnect.rest.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service d'authentification
 * Chemin: src/main/java/com/mediconnect/rest/service/AuthService.java
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    /**
     * Authentifier un utilisateur
     * NOTE: Version simplifiée - à adapter selon votre logique d'authentification
     */
    public LoginResponse login(LoginRequest request) {
        log.info("Tentative de connexion pour: {}", request.getEmail());

        // TODO: Récupérer l'utilisateur depuis la base via SOAP
        // Pour l'instant, simulation avec des credentials en dur

        // Exemple: admin@mediconnect.com / admin123
        if ("admin@mediconnect.com".equals(request.getEmail()) &&
                "admin123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getEmail(), "ADMIN", 1L);

            return LoginResponse.builder()
                    .token(token)
                    .type("Bearer")
                    .id(1L)
                    .email(request.getEmail())
                    .nom("Admin")
                    .prenom("System")
                    .role("ADMIN")
                    .build();
        }

        // Exemple: medecin@mediconnect.com / medecin123
        if ("medecin@mediconnect.com".equals(request.getEmail()) &&
                "medecin123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getEmail(), "MEDECIN", 2L);

            return LoginResponse.builder()
                    .token(token)
                    .type("Bearer")
                    .id(2L)
                    .email(request.getEmail())
                    .nom("Dupont")
                    .prenom("Jean")
                    .role("MEDECIN")
                    .build();
        }

        // Exemple: patient@mediconnect.com / patient123
        if ("patient@mediconnect.com".equals(request.getEmail()) &&
                "patient123".equals(request.getPassword())) {

            String token = jwtUtil.generateToken(request.getEmail(), "PATIENT", 3L);

            return LoginResponse.builder()
                    .token(token)
                    .type("Bearer")
                    .id(3L)
                    .email(request.getEmail())
                    .nom("Martin")
                    .prenom("Marie")
                    .role("PATIENT")
                    .build();
        }

        throw new RuntimeException("Email ou mot de passe incorrect");
    }

    /**
     * Vérifier si un token est valide
     */
    public boolean validateToken(String token) {
        try {
            String email = jwtUtil.extractEmail(token);
            return !jwtUtil.isTokenExpired(token);
        } catch (Exception e) {
            log.error("Token invalide", e);
            return false;
        }
    }
}