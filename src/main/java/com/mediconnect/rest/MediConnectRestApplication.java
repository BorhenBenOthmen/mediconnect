package com.mediconnect.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application principale du service REST MediConnect
 * API moderne pour Web/Mobile qui consomme le service SOAP
 * Chemin: src/main/java/com/mediconnect/rest/MediConnectRestApplication.java
 */
@SpringBootApplication
public class MediConnectRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediConnectRestApplication.class, args);
        System.out.println("✅ MediConnect REST API démarrée sur http://localhost:8081");
        System.out.println("📋 Swagger UI disponible sur : http://localhost:8081/swagger-ui.html");
        System.out.println("🔐 Endpoints d'authentification : http://localhost:8081/api/auth");
    }
}