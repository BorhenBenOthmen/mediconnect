package com.mediconnect.rest.soap;

import com.mediconnect.rest.soap.client.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.net.URL;

/**
 * Client SOAP pour communiquer avec le service MediConnect
 * Chemin: src/main/java/com/mediconnect/rest/soap/MediConnectSoapClient.java
 */
@Component
@Slf4j
public class MediConnectSoapClient {

    @Value("${soap.client.url}")
    private String soapServiceUrl;

    private MediConnectPort port;

    /**
     * IMPORTANT: Utiliser @PostConstruct au lieu du constructeur
     * pour que Spring injecte @Value avant l'initialisation
     */
    @PostConstruct
    public void init() {
        try {
            log.info("🔧 Initialisation du client SOAP...");
            log.info("📍 URL SOAP configurée: {}", soapServiceUrl);

            // Créer l'URL complète du WSDL
            String wsdlUrl = soapServiceUrl + "/mediconnect.wsdl";
            log.info("📄 WSDL URL: {}", wsdlUrl);

            // Créer le service SOAP
            URL url = new URL(wsdlUrl);
            MediConnectPortService service = new MediConnectPortService(url);

            // Obtenir le port (nom exact de la méthode générée par JAXWS)
            this.port = service.getMediConnectPortSoap11();

            log.info("✅ Client SOAP initialisé avec succès!");

        } catch (Exception e) {
            log.error("❌ ERREUR lors de l'initialisation du client SOAP", e);
            log.error("   URL configurée: {}", soapServiceUrl);
            log.error("   Message: {}", e.getMessage());
            throw new RuntimeException("Impossible de se connecter au service SOAP: " + e.getMessage(), e);
        }
    }

    // ==================== PATIENT ====================

    public CreerPatientResponse creerPatient(CreerPatientRequest request) {
        log.debug("🔵 SOAP Call: creerPatient");
        try {
            return port.creerPatient(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP creerPatient", e);
            throw new RuntimeException("Erreur lors de la création du patient", e);
        }
    }

    public ObtenirPatientResponse obtenirPatient(ObtenirPatientRequest request) {
        log.debug("🔵 SOAP Call: obtenirPatient");
        try {
            return port.obtenirPatient(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP obtenirPatient", e);
            throw new RuntimeException("Erreur lors de la récupération du patient", e);
        }
    }

    public ListerPatientsResponse listerPatients(ListerPatientsRequest request) {
        log.debug("🔵 SOAP Call: listerPatients");
        try {
            return port.listerPatients(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP listerPatients", e);
            throw new RuntimeException("Erreur lors du listage des patients", e);
        }
    }

    // ==================== MÉDECIN ====================

    public CreerMedecinResponse creerMedecin(CreerMedecinRequest request) {
        log.debug("🔵 SOAP Call: creerMedecin");
        try {
            return port.creerMedecin(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP creerMedecin", e);
            throw new RuntimeException("Erreur lors de la création du médecin", e);
        }
    }

    public ObtenirMedecinResponse obtenirMedecin(ObtenirMedecinRequest request) {
        log.debug("🔵 SOAP Call: obtenirMedecin");
        try {
            return port.obtenirMedecin(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP obtenirMedecin", e);
            throw new RuntimeException("Erreur lors de la récupération du médecin", e);
        }
    }

    public ListerMedecinsResponse listerMedecins(ListerMedecinsRequest request) {
        log.debug("🔵 SOAP Call: listerMedecins");
        try {
            return port.listerMedecins(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP listerMedecins", e);
            throw new RuntimeException("Erreur lors du listage des médecins", e);
        }
    }

    // ==================== RENDEZ-VOUS ====================

    public CreerRendezVousResponse creerRendezVous(CreerRendezVousRequest request) {
        log.debug("🔵 SOAP Call: creerRendezVous");
        try {
            return port.creerRendezVous(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP creerRendezVous", e);
            throw new RuntimeException("Erreur lors de la création du rendez-vous", e);
        }
    }

    public ObtenirRendezVousResponse obtenirRendezVous(ObtenirRendezVousRequest request) {
        log.debug("🔵 SOAP Call: obtenirRendezVous");
        try {
            return port.obtenirRendezVous(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP obtenirRendezVous", e);
            throw new RuntimeException("Erreur lors de la récupération du rendez-vous", e);
        }
    }

    public ListerRendezVousParMedecinResponse listerRendezVousParMedecin(ListerRendezVousParMedecinRequest request) {
        log.debug("🔵 SOAP Call: listerRendezVousParMedecin");
        try {
            return port.listerRendezVousParMedecin(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP listerRendezVousParMedecin", e);
            throw new RuntimeException("Erreur lors du listage des rendez-vous", e);
        }
    }

    // ==================== DOSSIER MÉDICAL ====================

    public CreerDossierMedicalResponse creerDossierMedical(CreerDossierMedicalRequest request) {
        log.debug("🔵 SOAP Call: creerDossierMedical");
        try {
            return port.creerDossierMedical(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP creerDossierMedical", e);
            throw new RuntimeException("Erreur lors de la création du dossier médical", e);
        }
    }

    public ObtenirDossiersParPatientResponse obtenirDossiersParPatient(ObtenirDossiersParPatientRequest request) {
        log.debug("🔵 SOAP Call: obtenirDossiersParPatient");
        try {
            return port.obtenirDossiersParPatient(request);
        } catch (Exception e) {
            log.error("❌ Erreur SOAP obtenirDossiersParPatient", e);
            throw new RuntimeException("Erreur lors de la récupération des dossiers", e);
        }
    }
}