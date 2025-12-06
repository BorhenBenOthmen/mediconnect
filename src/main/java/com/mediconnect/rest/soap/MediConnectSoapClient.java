package com.mediconnect.rest.soap;

import com.mediconnect.rest.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;

/**
 * Client SOAP pour communiquer avec le service SOAP
 * NOTE: Cette classe utilise les classes générées depuis le WSDL
 * Vous devez d'abord copier le WSDL et exécuter: mvn generate-sources
 *
 * Chemin: src/main/java/com/mediconnect/rest/soap/MediConnectSoapClient.java
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MediConnectSoapClient {

    private final WebServiceTemplate webServiceTemplate;

    // ============================================
    // PATIENTS
    // ============================================

    /**
     * Créer un patient via SOAP
     */
    public PatientDTO creerPatient(PatientDTO patientDTO) {
        log.info("Appel SOAP: Créer patient - {}", patientDTO.getEmail());

        // TODO: Convertir PatientDTO -> CreerPatientRequest
        // TODO: Appeler le service SOAP
        // TODO: Convertir la réponse -> PatientDTO

        // Version temporaire sans classes générées
        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP. " +
                        "Exécutez: mvn generate-sources"
        );
    }

    /**
     * Obtenir un patient par ID via SOAP
     */
    public PatientDTO obtenirPatient(Long id) {
        log.info("Appel SOAP: Obtenir patient - ID: {}", id);

        // TODO: Implémenter après génération des classes
        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    /**
     * Lister tous les patients via SOAP
     */
    public List<PatientDTO> listerPatients() {
        log.info("Appel SOAP: Lister tous les patients");

        // TODO: Implémenter après génération des classes
        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    // ============================================
    // MÉDECINS
    // ============================================

    /**
     * Créer un médecin via SOAP
     */
    public MedecinDTO creerMedecin(MedecinDTO medecinDTO) {
        log.info("Appel SOAP: Créer médecin - {}", medecinDTO.getEmail());

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    /**
     * Obtenir un médecin par ID via SOAP
     */
    public MedecinDTO obtenirMedecin(Long id) {
        log.info("Appel SOAP: Obtenir médecin - ID: {}", id);

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    /**
     * Lister tous les médecins via SOAP
     */
    public List<MedecinDTO> listerMedecins() {
        log.info("Appel SOAP: Lister tous les médecins");

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    // ============================================
    // RENDEZ-VOUS
    // ============================================

    /**
     * Créer un rendez-vous via SOAP
     */
    public RendezVousDTO creerRendezVous(RendezVousDTO rendezVousDTO) {
        log.info("Appel SOAP: Créer rendez-vous");

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    /**
     * Lister les rendez-vous d'un médecin via SOAP
     */
    public List<RendezVousDTO> listerRendezVousParMedecin(Long medecinId) {
        log.info("Appel SOAP: Lister rendez-vous du médecin - ID: {}", medecinId);

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    // ============================================
    // DOSSIERS MÉDICAUX
    // ============================================

    /**
     * Créer un dossier médical via SOAP
     */
    public DossierMedicalDTO creerDossierMedical(DossierMedicalDTO dossierDTO) {
        log.info("Appel SOAP: Créer dossier médical");

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }

    /**
     * Obtenir les dossiers d'un patient via SOAP
     */
    public List<DossierMedicalDTO> obtenirDossiersParPatient(Long patientId) {
        log.info("Appel SOAP: Obtenir dossiers du patient - ID: {}", patientId);

        throw new UnsupportedOperationException(
                "Méthode à implémenter après génération des classes SOAP"
        );
    }
}

/*
 * INSTRUCTIONS POUR COMPLÉTER CE CLIENT:
 *
 * 1. Démarrer le service SOAP sur http://localhost:8080
 *
 * 2. Récupérer le WSDL:
 *    - Ouvrir: http://localhost:8080/ws/mediconnect.wsdl
 *    - Sauvegarder dans: src/main/resources/wsdl/mediconnect.wsdl
 *
 * 3. Générer les classes Java:
 *    - Exécuter: mvn generate-sources
 *    - Les classes seront dans: target/generated-sources/jaxb/
 *
 * 4. Implémenter les méthodes:
 *    Exemple pour creerPatient():
 *
 *    CreerPatientRequest request = new CreerPatientRequest();
 *    Patient patient = new Patient();
 *    patient.setNom(patientDTO.getNom());
 *    patient.setPrenom(patientDTO.getPrenom());
 *    // ... mapper tous les champs
 *    request.setPatient(patient);
 *
 *    CreerPatientResponse response = (CreerPatientResponse)
 *        webServiceTemplate.marshalSendAndReceive(request);
 *
 *    return convertToDTO(response.getPatient());
 */