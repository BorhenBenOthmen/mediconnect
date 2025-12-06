package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.RendezVousDTO;
import com.mediconnect.rest.soap.MediConnectSoapClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service REST pour gérer les rendez-vous
 * Consomme le service SOAP
 * Chemin: src/main/java/com/mediconnect/rest/service/RendezVousService.java
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class RendezVousService {

    private final MediConnectSoapClient soapClient;

    /**
     * Créer un nouveau rendez-vous
     */
    public RendezVousDTO creerRendezVous(RendezVousDTO rendezVousDTO) {
        log.info("REST -> SOAP: Création rendez-vous");
        return soapClient.creerRendezVous(rendezVousDTO);
    }

    /**
     * Lister les rendez-vous d'un médecin
     */
    public List<RendezVousDTO> listerRendezVousParMedecin(Long medecinId) {
        log.info("REST -> SOAP: Liste rendez-vous médecin ID: {}", medecinId);
        return soapClient.listerRendezVousParMedecin(medecinId);
    }
}