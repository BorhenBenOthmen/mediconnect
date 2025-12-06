package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.DossierMedicalDTO;
import com.mediconnect.rest.soap.MediConnectSoapClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service REST pour gérer les dossiers médicaux
 * Consomme le service SOAP
 * Chemin: src/main/java/com/mediconnect/rest/service/DossierMedicalService.java
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class DossierMedicalService {

    private final MediConnectSoapClient soapClient;

    /**
     * Créer un nouveau dossier médical
     */
    public DossierMedicalDTO creerDossierMedical(DossierMedicalDTO dossierDTO) {
        log.info("REST -> SOAP: Création dossier médical");
        return soapClient.creerDossierMedical(dossierDTO);
    }

    /**
     * Obtenir les dossiers d'un patient
     */
    public List<DossierMedicalDTO> obtenirDossiersParPatient(Long patientId) {
        log.info("REST -> SOAP: Récupération dossiers patient ID: {}", patientId);
        return soapClient.obtenirDossiersParPatient(patientId);
    }
}