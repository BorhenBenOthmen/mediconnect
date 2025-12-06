package com.mediconnect.rest.controller;

import com.mediconnect.rest.dto.LoginRequest;
import com.mediconnect.rest.dto.LoginResponse;
import com.mediconnect.rest.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller REST pour l'authentification
 * Chemin: src/main/java/com/mediconnect/rest/controller/AuthController.java
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    /**
     * POST /api/auth/login
     * Authentifier un utilisateur et obtenir un token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET /api/auth/validate
     * Valider un token JWT
     */
    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String authHeader) {
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            boolean isValid = authService.validateToken(token);
            return ResponseEntity.ok(isValid);
        }
        return ResponseEntity.ok(false);
    }
}

/**
 * EXEMPLES D'UTILISATION:
 *
 * 1. Login (POST /api/auth/login):
 *
 * Request:
 * {
 *   "email": "admin@mediconnect.com",
 *   "password": "admin123"
 * }
 *
 * Response:
 * {
 *   "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
 *   "type": "Bearer",
 *   "id": 1,
 *   "email": "admin@mediconnect.com",
 *   "nom": "Admin",
 *   "prenom": "System",
 *   "role": "ADMIN"
 * }
 *
 * 2. Credentials de test:
 * - Admin: admin@mediconnect.com / admin123
 * - Médecin: medecin@mediconnect.com / medecin123
 * - Patient: patient@mediconnect.com / patient123
 */