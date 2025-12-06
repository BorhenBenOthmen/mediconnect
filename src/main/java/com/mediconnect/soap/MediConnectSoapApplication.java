package com.mediconnect.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application principale du service SOAP MediConnect
 * Point d'entrée du système historique interne
 */
@SpringBootApplication
public class MediConnectSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediConnectSoapApplication.class, args);
        System.out.println("✅ MediConnect SOAP Service démarré sur http://localhost:8080");
        System.out.println("📋 WSDL disponible sur : http://localhost:8080/ws/mediconnect.wsdl");
    }
}