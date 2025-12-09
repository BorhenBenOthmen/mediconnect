package com.mediconnect.rest.config;

import com.mediconnect.rest.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * Configuration de sécurité Spring Security + JWT
 * Chemin: src/main/java/com/mediconnect/rest/config/SecurityConfig.java
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        // Endpoints publics (authentification)
                        .requestMatchers("/api/auth/**").permitAll()

                        // Actuator endpoints
                        .requestMatchers("/actuator/**").permitAll()

                        // Swagger UI (si configuré)
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // ⚠️ TEMPORAIRE : Tous les endpoints API sont publics pour TESTER
                        // À SÉCURISER en production avec les rôles appropriés !
                        .requestMatchers("/api/**").permitAll()

                        /* VERSION SÉCURISÉE (à réactiver une fois les utilisateurs créés) :
                        .requestMatchers("/api/patients/**").hasAnyRole("PATIENT", "MEDECIN", "ADMIN")
                        .requestMatchers("/api/medecins/**").hasAnyRole("MEDECIN", "ADMIN")
                        .requestMatchers("/api/rendez-vous/**").hasAnyRole("PATIENT", "MEDECIN", "ADMIN")
                        .requestMatchers("/api/dossiers-medicaux/**").hasAnyRole("MEDECIN", "ADMIN")
                        */

                        // Toutes les autres requêtes nécessitent une authentification
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // ✅ CORRECTION : Ajout de localhost:5173 (Vite/React)
        configuration.setAllowedOrigins(Arrays.asList(
                "http://localhost:5173",  // ← AJOUTÉ : Vite
                "http://localhost:3000",  // Create React App
                "http://localhost:4200"   // Angular
        ));

        // Méthodes HTTP autorisées
        configuration.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
        ));

        // Headers autorisés (plus complets)
        configuration.setAllowedHeaders(Arrays.asList(
                "Authorization",
                "Content-Type",
                "X-Requested-With",
                "Accept",
                "Origin",
                "Access-Control-Request-Method",
                "Access-Control-Request-Headers"
        ));

        // Headers exposés
        configuration.setExposedHeaders(Arrays.asList(
                "Authorization",
                "Content-Disposition"
        ));

        // Autoriser les credentials
        configuration.setAllowCredentials(true);

        // Durée de cache (1 heure)
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}