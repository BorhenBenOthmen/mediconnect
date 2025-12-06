package com.mediconnect.rest.service;

import com.mediconnect.rest.dto.PatientDTO;
import com.mediconnect.rest.soap.MediConnectSoapClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service REST pour gérer les patients
 * Consomme le service SOAP
 * Chemin: src/main/java/com/mediconnect/rest/service/PatientService.java
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService {

    private final MediConnectSoapClient soapClient;

    /**
     * Créer un nouveau patient
     */
    public PatientDTO creerPatient(PatientDTO patientDTO) {
        log.info("REST -> SOAP: Création patient {}", patientDTO.getEmail());
        return soapClient.creerPatient(patientDTO);
    }

    /**
     * Obtenir un patient par ID
     */
    public PatientDTO obtenirPatient(Long id) {
        log.info("REST -> SOAP: Récupération patient ID: {}", id);
        return soapClient.obtenirPatient(id);
    }

    /**
     * Lister tous les patients
     */
    public List<PatientDTO> listerPatients() {
        log.info("REST -> SOAP: Liste de tous les patients");
        return soapClient.listerPatients();
    }
}