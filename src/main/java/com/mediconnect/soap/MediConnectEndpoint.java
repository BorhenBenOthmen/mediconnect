package com.mediconnect.soap;

import com.mediconnect.soap.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

// IMPORTS EXPLICITES DES CLASSES GÉNÉRÉES (XML/SOAP)
import com.mediconnect.soap.generated.CreerPatientRequest;
import com.mediconnect.soap.generated.CreerPatientResponse;
import com.mediconnect.soap.generated.ObtenirPatientRequest;
import com.mediconnect.soap.generated.ObtenirPatientResponse;
import com.mediconnect.soap.generated.ListerPatientsRequest;
import com.mediconnect.soap.generated.ListerPatientsResponse;
import com.mediconnect.soap.generated.CreerMedecinRequest;
import com.mediconnect.soap.generated.CreerMedecinResponse;
import com.mediconnect.soap.generated.ObtenirMedecinRequest;
import com.mediconnect.soap.generated.ObtenirMedecinResponse;
import com.mediconnect.soap.generated.ListerMedecinsRequest;
import com.mediconnect.soap.generated.ListerMedecinsResponse;
import com.mediconnect.soap.generated.CreerRendezVousRequest;
import com.mediconnect.soap.generated.CreerRendezVousResponse;
import com.mediconnect.soap.generated.ObtenirRendezVousRequest;
import com.mediconnect.soap.generated.ObtenirRendezVousResponse;
import com.mediconnect.soap.generated.ListerRendezVousParMedecinRequest;
import com.mediconnect.soap.generated.ListerRendezVousParMedecinResponse;
import com.mediconnect.soap.generated.CreerDossierMedicalRequest;
import com.mediconnect.soap.generated.CreerDossierMedicalResponse;
import com.mediconnect.soap.generated.ObtenirDossiersParPatientRequest;
import com.mediconnect.soap.generated.ObtenirDossiersParPatientResponse;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Endpoint SOAP principal pour MediConnect
 * ⚠️ CORRECTION: Supprimer @Component - @Endpoint suffit
 */
@Endpoint  // ✅ @Endpoint inclut déjà @Component
@RequiredArgsConstructor
public class MediConnectEndpoint {

    private static final String NAMESPACE_URI = "http://mediconnect.com/soap/services";

    private final PatientService patientService;
    private final MedecinService medecinService;
    private final RendezVousService rendezVousService;
    private final DossierMedicalService dossierMedicalService;

    // ============================================
    // PATIENTS
    // ============================================

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreerPatientRequest")
    @ResponsePayload
    public CreerPatientResponse creerPatient(@RequestPayload CreerPatientRequest request) {
        // Convertir XML → Entité JPA
        com.mediconnect.soap.entity.Patient patientEntity = convertToEntity(request.getPatient());

        // Sauvegarder dans la BD
        com.mediconnect.soap.entity.Patient savedPatient = patientService.creerPatient(patientEntity);

        // Convertir Entité → XML
        CreerPatientResponse response = new CreerPatientResponse();
        response.setPatient(convertToXml(savedPatient));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenirPatientRequest")
    @ResponsePayload
    public ObtenirPatientResponse obtenirPatient(@RequestPayload ObtenirPatientRequest request) {
        com.mediconnect.soap.entity.Patient patientEntity = patientService.obtenirPatient(request.getId());

        ObtenirPatientResponse response = new ObtenirPatientResponse();
        response.setPatient(convertToXml(patientEntity));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListerPatientsRequest")
    @ResponsePayload
    public ListerPatientsResponse listerPatients(@RequestPayload ListerPatientsRequest request) {
        List<com.mediconnect.soap.entity.Patient> patientsEntities = patientService.listerPatients();

        ListerPatientsResponse response = new ListerPatientsResponse();
        response.getPatients().addAll(
                patientsEntities.stream().map(this::convertToXml).collect(Collectors.toList())
        );
        return response;
    }

    // ============================================
    // MÉDECINS
    // ============================================

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreerMedecinRequest")
    @ResponsePayload
    public CreerMedecinResponse creerMedecin(@RequestPayload CreerMedecinRequest request) {
        com.mediconnect.soap.entity.Medecin medecinEntity = convertToEntity(request.getMedecin());
        com.mediconnect.soap.entity.Medecin savedMedecin = medecinService.creerMedecin(medecinEntity);

        CreerMedecinResponse response = new CreerMedecinResponse();
        response.setMedecin(convertToXml(savedMedecin));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenirMedecinRequest")
    @ResponsePayload
    public ObtenirMedecinResponse obtenirMedecin(@RequestPayload ObtenirMedecinRequest request) {
        com.mediconnect.soap.entity.Medecin medecinEntity = medecinService.obtenirMedecin(request.getId());

        ObtenirMedecinResponse response = new ObtenirMedecinResponse();
        response.setMedecin(convertToXml(medecinEntity));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListerMedecinsRequest")
    @ResponsePayload
    public ListerMedecinsResponse listerMedecins(@RequestPayload ListerMedecinsRequest request) {
        List<com.mediconnect.soap.entity.Medecin> medecinsEntities = medecinService.listerMedecins();

        ListerMedecinsResponse response = new ListerMedecinsResponse();
        response.getMedecins().addAll(
                medecinsEntities.stream().map(this::convertToXml).collect(Collectors.toList())
        );
        return response;
    }

    // ============================================
    // RENDEZ-VOUS
    // ============================================

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreerRendezVousRequest")
    @ResponsePayload
    public CreerRendezVousResponse creerRendezVous(@RequestPayload CreerRendezVousRequest request) {
        com.mediconnect.soap.entity.RendezVous rendezVousEntity = convertToEntity(request.getRendezVous());
        com.mediconnect.soap.entity.RendezVous savedRendezVous = rendezVousService.creerRendezVous(rendezVousEntity);

        CreerRendezVousResponse response = new CreerRendezVousResponse();
        response.setRendezVous(convertToXml(savedRendezVous));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenirRendezVousRequest")
    @ResponsePayload
    public ObtenirRendezVousResponse obtenirRendezVous(@RequestPayload ObtenirRendezVousRequest request) {
        com.mediconnect.soap.entity.RendezVous rendezVousEntity = rendezVousService.obtenirRendezVous(request.getId());

        ObtenirRendezVousResponse response = new ObtenirRendezVousResponse();
        response.setRendezVous(convertToXml(rendezVousEntity));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ListerRendezVousParMedecinRequest")
    @ResponsePayload
    public ListerRendezVousParMedecinResponse listerRendezVousParMedecin(@RequestPayload ListerRendezVousParMedecinRequest request) {
        List<com.mediconnect.soap.entity.RendezVous> rendezVousList = rendezVousService.listerRendezVousParMedecin(request.getMedecinId());

        ListerRendezVousParMedecinResponse response = new ListerRendezVousParMedecinResponse();
        response.getRendezVous().addAll(
                rendezVousList.stream().map(this::convertToXml).collect(Collectors.toList())
        );
        return response;
    }

    // ============================================
    // DOSSIERS MÉDICAUX
    // ============================================

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "CreerDossierMedicalRequest")
    @ResponsePayload
    public CreerDossierMedicalResponse creerDossierMedical(@RequestPayload CreerDossierMedicalRequest request) {
        com.mediconnect.soap.entity.DossierMedical dossierEntity = convertToEntity(request.getDossierMedical());
        com.mediconnect.soap.entity.DossierMedical savedDossier = dossierMedicalService.creerDossierMedical(dossierEntity);

        CreerDossierMedicalResponse response = new CreerDossierMedicalResponse();
        response.setDossierMedical(convertToXml(savedDossier));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "ObtenirDossiersParPatientRequest")
    @ResponsePayload
    public ObtenirDossiersParPatientResponse obtenirDossiersParPatient(@RequestPayload ObtenirDossiersParPatientRequest request) {
        List<com.mediconnect.soap.entity.DossierMedical> dossiers = dossierMedicalService.obtenirDossiersParPatient(request.getPatientId());

        ObtenirDossiersParPatientResponse response = new ObtenirDossiersParPatientResponse();
        response.getDossiersMedicaux().addAll(
                dossiers.stream().map(this::convertToXml).collect(Collectors.toList())
        );
        return response;
    }

    // ============================================
    // MÉTHODES DE CONVERSION: ENTITY → XML
    // ============================================

    private com.mediconnect.soap.generated.Patient convertToXml(com.mediconnect.soap.entity.Patient patientEntity) {
        com.mediconnect.soap.generated.Patient xmlPatient = new com.mediconnect.soap.generated.Patient();
        xmlPatient.setId(patientEntity.getId());
        xmlPatient.setNom(patientEntity.getNom());
        xmlPatient.setPrenom(patientEntity.getPrenom());
        xmlPatient.setDateNaissance(convertToXmlDate(patientEntity.getDateNaissance()));
        xmlPatient.setTelephone(patientEntity.getTelephone());
        xmlPatient.setEmail(patientEntity.getEmail());
        xmlPatient.setAdresse(patientEntity.getAdresse());
        xmlPatient.setNumeroSecuriteSociale(patientEntity.getNumeroSecuriteSociale());
        return xmlPatient;
    }

    private com.mediconnect.soap.generated.Medecin convertToXml(com.mediconnect.soap.entity.Medecin medecinEntity) {
        com.mediconnect.soap.generated.Medecin xmlMedecin = new com.mediconnect.soap.generated.Medecin();
        xmlMedecin.setId(medecinEntity.getId());
        xmlMedecin.setNom(medecinEntity.getNom());
        xmlMedecin.setPrenom(medecinEntity.getPrenom());
        xmlMedecin.setSpecialite(medecinEntity.getSpecialite());
        xmlMedecin.setTelephone(medecinEntity.getTelephone());
        xmlMedecin.setEmail(medecinEntity.getEmail());
        xmlMedecin.setNumeroOrdre(medecinEntity.getNumeroOrdre());
        return xmlMedecin;
    }

    private com.mediconnect.soap.generated.RendezVous convertToXml(com.mediconnect.soap.entity.RendezVous rdvEntity) {
        com.mediconnect.soap.generated.RendezVous xmlRdv = new com.mediconnect.soap.generated.RendezVous();
        xmlRdv.setId(rdvEntity.getId());
        xmlRdv.setPatientId(rdvEntity.getPatientId());
        xmlRdv.setMedecinId(rdvEntity.getMedecinId());
        xmlRdv.setDateRendezVous(convertToXmlDateTime(rdvEntity.getDateRendezVous()));
        xmlRdv.setMotif(rdvEntity.getMotif());
        xmlRdv.setStatut(rdvEntity.getStatut());
        return xmlRdv;
    }

    private com.mediconnect.soap.generated.DossierMedical convertToXml(com.mediconnect.soap.entity.DossierMedical dossierEntity) {
        com.mediconnect.soap.generated.DossierMedical xmlDossier = new com.mediconnect.soap.generated.DossierMedical();
        xmlDossier.setId(dossierEntity.getId());
        xmlDossier.setPatientId(dossierEntity.getPatientId());
        xmlDossier.setMedecinId(dossierEntity.getMedecinId());
        xmlDossier.setDateConsultation(convertToXmlDateTime(dossierEntity.getDateConsultation()));
        xmlDossier.setDiagnostic(dossierEntity.getDiagnostic());
        xmlDossier.setTraitement(dossierEntity.getTraitement());
        xmlDossier.setNotes(dossierEntity.getNotes());
        return xmlDossier;
    }

    // ============================================
    // MÉTHODES DE CONVERSION: XML → ENTITY
    // ============================================

    private com.mediconnect.soap.entity.Patient convertToEntity(com.mediconnect.soap.generated.Patient xmlPatient) {
        com.mediconnect.soap.entity.Patient patientEntity = new com.mediconnect.soap.entity.Patient();
        patientEntity.setId(xmlPatient.getId());
        patientEntity.setNom(xmlPatient.getNom());
        patientEntity.setPrenom(xmlPatient.getPrenom());
        patientEntity.setDateNaissance(convertToLocalDate(xmlPatient.getDateNaissance()));
        patientEntity.setTelephone(xmlPatient.getTelephone());
        patientEntity.setEmail(xmlPatient.getEmail());
        patientEntity.setAdresse(xmlPatient.getAdresse());
        patientEntity.setNumeroSecuriteSociale(xmlPatient.getNumeroSecuriteSociale());
        return patientEntity;
    }

    private com.mediconnect.soap.entity.Medecin convertToEntity(com.mediconnect.soap.generated.Medecin xmlMedecin) {
        com.mediconnect.soap.entity.Medecin medecinEntity = new com.mediconnect.soap.entity.Medecin();
        medecinEntity.setId(xmlMedecin.getId());
        medecinEntity.setNom(xmlMedecin.getNom());
        medecinEntity.setPrenom(xmlMedecin.getPrenom());
        medecinEntity.setSpecialite(xmlMedecin.getSpecialite());
        medecinEntity.setTelephone(xmlMedecin.getTelephone());
        medecinEntity.setEmail(xmlMedecin.getEmail());
        medecinEntity.setNumeroOrdre(xmlMedecin.getNumeroOrdre());
        return medecinEntity;
    }

    private com.mediconnect.soap.entity.RendezVous convertToEntity(com.mediconnect.soap.generated.RendezVous xmlRdv) {
        com.mediconnect.soap.entity.RendezVous rdvEntity = new com.mediconnect.soap.entity.RendezVous();
        rdvEntity.setId(xmlRdv.getId());
        rdvEntity.setPatientId(xmlRdv.getPatientId());
        rdvEntity.setMedecinId(xmlRdv.getMedecinId());
        rdvEntity.setDateRendezVous(convertToLocalDateTime(xmlRdv.getDateRendezVous()));
        rdvEntity.setMotif(xmlRdv.getMotif());
        rdvEntity.setStatut(xmlRdv.getStatut());
        return rdvEntity;
    }

    private com.mediconnect.soap.entity.DossierMedical convertToEntity(com.mediconnect.soap.generated.DossierMedical xmlDossier) {
        com.mediconnect.soap.entity.DossierMedical dossierEntity = new com.mediconnect.soap.entity.DossierMedical();
        dossierEntity.setId(xmlDossier.getId());
        dossierEntity.setPatientId(xmlDossier.getPatientId());
        dossierEntity.setMedecinId(xmlDossier.getMedecinId());
        dossierEntity.setDateConsultation(convertToLocalDateTime(xmlDossier.getDateConsultation()));
        dossierEntity.setDiagnostic(xmlDossier.getDiagnostic());
        dossierEntity.setTraitement(xmlDossier.getTraitement());
        dossierEntity.setNotes(xmlDossier.getNotes());
        return dossierEntity;
    }

    // ============================================
    // UTILITAIRES DE CONVERSION DE DATES
    // ============================================

    private XMLGregorianCalendar convertToXmlDate(LocalDate date) {
        try {
            GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de conversion de date", e);
        }
    }

    private XMLGregorianCalendar convertToXmlDateTime(LocalDateTime dateTime) {
        try {
            GregorianCalendar gcal = GregorianCalendar.from(dateTime.atZone(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (Exception e) {
            throw new RuntimeException("Erreur de conversion de date/heure", e);
        }
    }

    private LocalDate convertToLocalDate(XMLGregorianCalendar xmlDate) {
        return xmlDate.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    private LocalDateTime convertToLocalDateTime(XMLGregorianCalendar xmlDateTime) {
        return xmlDateTime.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }
}