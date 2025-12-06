package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.MedecinDTO;
import com.mediconnect.rest.soap.MediConnectSoapClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service REST pour gérer les médecins
 * Consomme le service SOAP
 * Chemin: src/main/java/com/mediconnect/rest/service/MedecinService.java
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MedecinService {

    private final MediConnectSoapClient soapClient;

    /**
     * Créer un nouveau médecin
     */
    public MedecinDTO creerMedecin(MedecinDTO medecinDTO) {
        log.info("REST -> SOAP: Création médecin {}", medecinDTO.getEmail());
        return soapClient.creerMedecin(medecinDTO);
    }

    /**
     * Obtenir un médecin par ID
     */
    public MedecinDTO obtenirMedecin(Long id) {
        log.info("REST -> SOAP: Récupération médecin ID: {}", id);
        return soapClient.obtenirMedecin(id);
    }

    /**
     * Lister tous les médecins
     */
    public List<MedecinDTO> listerMedecins() {
        log.info("REST -> SOAP: Liste de tous les médecins");
        return soapClient.listerMedecins();
    }
}