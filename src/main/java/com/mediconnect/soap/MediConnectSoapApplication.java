package com.mediconnect.soap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.mediconnect.soap",
        "com.mediconnect.soap.endpoint"   // ← obligatoire sinon pas détecté
})
public class MediConnectSoapApplication {

    public static void main(String[] args) {
        SpringApplication.run(MediConnectSoapApplication.class, args);
        System.out.println("MediConnect SOAP Service démarré sur http://localhost:8080");
        System.out.println("WSDL disponible sur : http://localhost:8080/ws/mediconnect.wsdl");
    }
}