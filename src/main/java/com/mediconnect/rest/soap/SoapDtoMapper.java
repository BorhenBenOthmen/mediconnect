package com.mediconnect.rest.soap;

import com.mediconnect.rest.dto.*;
import com.mediconnect.rest.soap.client.*;
import org.springframework.stereotype.Component;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper pour convertir entre DTOs REST et objets SOAP
 * Chemin: src/main/java/com/mediconnect/rest/soap/SoapDtoMapper.java
 */
@Component
public class SoapDtoMapper {

    // ==================== CONVERSIONS DATES ====================

    public XMLGregorianCalendar toXMLGregorianCalendar(LocalDate date) {
        if (date == null) return null;
        try {
            GregorianCalendar gcal = GregorianCalendar.from(date.atStartOfDay(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (Exception e) {
            throw new RuntimeException("Erreur conversion date", e);
        }
    }

    public XMLGregorianCalendar toXMLGregorianCalendar(LocalDateTime dateTime) {
        if (dateTime == null) return null;
        try {
            GregorianCalendar gcal = GregorianCalendar.from(dateTime.atZone(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gcal);
        } catch (Exception e) {
            throw new RuntimeException("Erreur conversion dateTime", e);
        }
    }

    public LocalDate toLocalDate(XMLGregorianCalendar xmlDate) {
        if (xmlDate == null) return null;
        return xmlDate.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }

    public LocalDateTime toLocalDateTime(XMLGregorianCalendar xmlDateTime) {
        if (xmlDateTime == null) return null;
        return xmlDateTime.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
    }

    // ==================== PATIENT MAPPINGS ====================

    public CreerPatientRequest patientDtoToCreerRequest(PatientDTO dto) {
        CreerPatientRequest request = new CreerPatientRequest();
        Patient patient = new Patient();

        patient.setNom(dto.getNom());
        patient.setPrenom(dto.getPrenom());
        patient.setDateNaissance(toXMLGregorianCalendar(dto.getDateNaissance()));
        patient.setTelephone(dto.getTelephone());
        patient.setEmail(dto.getEmail());
        patient.setAdresse(dto.getAdresse());
        patient.setNumeroSecuriteSociale(dto.getNumeroSecuriteSociale());

        request.setPatient(patient);
        return request;
    }

    public ObtenirPatientRequest buildObtenirPatientRequest(Long id) {
        ObtenirPatientRequest request = new ObtenirPatientRequest();
        request.setId(id);
        return request;
    }

    public PatientDTO soapPatientToDtoFrom(Patient patient) {
        if (patient == null) return null;

        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setPrenom(patient.getPrenom());
        dto.setDateNaissance(toLocalDate(patient.getDateNaissance()));
        dto.setTelephone(patient.getTelephone());
        dto.setEmail(patient.getEmail());
        dto.setAdresse(patient.getAdresse());
        dto.setNumeroSecuriteSociale(patient.getNumeroSecuriteSociale());

        return dto;
    }

    public List<PatientDTO> soapPatientListToDtoList(List<Patient> patients) {
        if (patients == null) return List.of();
        return patients.stream()
                .map(this::soapPatientToDtoFrom)
                .collect(Collectors.toList());
    }

    // ==================== MEDECIN MAPPINGS ====================

    public CreerMedecinRequest medecinDtoToCreerRequest(MedecinDTO dto) {
        CreerMedecinRequest request = new CreerMedecinRequest();
        Medecin medecin = new Medecin();

        medecin.setNom(dto.getNom());
        medecin.setPrenom(dto.getPrenom());
        medecin.setSpecialite(dto.getSpecialite());
        medecin.setTelephone(dto.getTelephone());
        medecin.setEmail(dto.getEmail());
        medecin.setNumeroOrdre(dto.getNumeroOrdre());

        request.setMedecin(medecin);
        return request;
    }

    public ObtenirMedecinRequest buildObtenirMedecinRequest(Long id) {
        ObtenirMedecinRequest request = new ObtenirMedecinRequest();
        request.setId(id);
        return request;
    }

    public MedecinDTO soapMedecinToDtoFrom(Medecin medecin) {
        if (medecin == null) return null;

        MedecinDTO dto = new MedecinDTO();
        dto.setId(medecin.getId());
        dto.setNom(medecin.getNom());
        dto.setPrenom(medecin.getPrenom());
        dto.setSpecialite(medecin.getSpecialite());
        dto.setTelephone(medecin.getTelephone());
        dto.setEmail(medecin.getEmail());
        dto.setNumeroOrdre(medecin.getNumeroOrdre());

        return dto;
    }

    public List<MedecinDTO> soapMedecinListToDtoList(List<Medecin> medecins) {
        if (medecins == null) return List.of();
        return medecins.stream()
                .map(this::soapMedecinToDtoFrom)
                .collect(Collectors.toList());
    }

    // ==================== RENDEZ-VOUS MAPPINGS ====================

    public CreerRendezVousRequest rendezVousDtoToCreerRequest(RendezVousDTO dto) {
        CreerRendezVousRequest request = new CreerRendezVousRequest();
        RendezVous rdv = new RendezVous();

        rdv.setPatientId(dto.getPatientId());
        rdv.setMedecinId(dto.getMedecinId());
        rdv.setDateRendezVous(toXMLGregorianCalendar(dto.getDateRendezVous()));
        rdv.setMotif(dto.getMotif());
        rdv.setStatut(dto.getStatut());

        request.setRendezVous(rdv);
        return request;
    }

    public ObtenirRendezVousRequest buildObtenirRendezVousRequest(Long id) {
        ObtenirRendezVousRequest request = new ObtenirRendezVousRequest();
        request.setId(id);
        return request;
    }

    public ListerRendezVousParMedecinRequest buildListerRendezVousRequest(Long medecinId) {
        ListerRendezVousParMedecinRequest request = new ListerRendezVousParMedecinRequest();
        request.setMedecinId(medecinId);
        return request;
    }

    public RendezVousDTO soapRendezVousToDtoFrom(RendezVous rdv) {
        if (rdv == null) return null;

        RendezVousDTO dto = new RendezVousDTO();
        dto.setId(rdv.getId());
        dto.setPatientId(rdv.getPatientId());
        dto.setMedecinId(rdv.getMedecinId());
        dto.setDateRendezVous(toLocalDateTime(rdv.getDateRendezVous()));
        dto.setMotif(rdv.getMotif());
        dto.setStatut(rdv.getStatut());

        return dto;
    }

    public List<RendezVousDTO> soapRendezVousListToDtoList(List<RendezVous> rdvList) {
        if (rdvList == null) return List.of();
        return rdvList.stream()
                .map(this::soapRendezVousToDtoFrom)
                .collect(Collectors.toList());
    }

    // ==================== DOSSIER MEDICAL MAPPINGS ====================

    public CreerDossierMedicalRequest dossierMedicalDtoToCreerRequest(DossierMedicalDTO dto) {
        CreerDossierMedicalRequest request = new CreerDossierMedicalRequest();
        DossierMedical dossier = new DossierMedical();

        dossier.setPatientId(dto.getPatientId());
        dossier.setMedecinId(dto.getMedecinId());
        dossier.setDateConsultation(toXMLGregorianCalendar(dto.getDateConsultation()));
        dossier.setDiagnostic(dto.getDiagnostic());
        dossier.setTraitement(dto.getTraitement());
        dossier.setNotes(dto.getNotes());

        request.setDossierMedical(dossier);
        return request;
    }

    public ObtenirDossiersParPatientRequest buildObtenirDossiersRequest(Long patientId) {
        ObtenirDossiersParPatientRequest request = new ObtenirDossiersParPatientRequest();
        request.setPatientId(patientId);
        return request;
    }

    public DossierMedicalDTO soapDossierMedicalToDtoFrom(DossierMedical dossier) {
        if (dossier == null) return null;

        DossierMedicalDTO dto = new DossierMedicalDTO();
        dto.setId(dossier.getId());
        dto.setPatientId(dossier.getPatientId());
        dto.setMedecinId(dossier.getMedecinId());
        dto.setDateConsultation(toLocalDateTime(dossier.getDateConsultation()));
        dto.setDiagnostic(dossier.getDiagnostic());
        dto.setTraitement(dossier.getTraitement());
        dto.setNotes(dossier.getNotes());

        return dto;
    }

    public List<DossierMedicalDTO> soapDossierMedicalListToDtoList(List<DossierMedical> dossiers) {
        if (dossiers == null) return List.of();
        return dossiers.stream()
                .map(this::soapDossierMedicalToDtoFrom)
                .collect(Collectors.toList());
    }

    // ==================== MÉTHODES SUPPLÉMENTAIRES POUR COMPATIBILITÉ ====================

    /**
     * Alias pour compatibilité avec l'ancien nom de méthode
     */
    public CreerPatientRequest patientDtoToCrerRequest(PatientDTO dto) {
        return patientDtoToCreerRequest(dto);
    }

    public CreerMedecinRequest medecinDtoToCrerRequest(MedecinDTO dto) {
        return medecinDtoToCreerRequest(dto);
    }

    public CreerRendezVousRequest rendezVousDtoToCrerRequest(RendezVousDTO dto) {
        return rendezVousDtoToCreerRequest(dto);
    }

    public CreerDossierMedicalRequest dossierDtoToCrerRequest(DossierMedicalDTO dto) {
        return dossierMedicalDtoToCreerRequest(dto);
    }

    public ListerPatientsRequest buildListerPatientsRequest() {
        return new ListerPatientsRequest();
    }

    public ListerMedecinsRequest buildListerMedecinsRequest() {
        return new ListerMedecinsRequest();
    }

    public DossierMedicalDTO soapDossierToDtoFrom(DossierMedical dossier) {
        return soapDossierMedicalToDtoFrom(dossier);
    }

    public List<DossierMedicalDTO> soapDossierListToDtoList(List<DossierMedical> dossiers) {
        return soapDossierMedicalListToDtoList(dossiers);
    }

    /**
     * Convertit CreerDossierMedicalResponse en DossierMedicalDTO
     */
    public DossierMedicalDTO creerDossierResponseToDto(CreerDossierMedicalResponse response) {
        if (response == null || response.getDossierMedical() == null) {
            return null;
        }
        return soapDossierMedicalToDtoFrom(response.getDossierMedical());
    }

    /**
     * Convertit ObtenirDossiersParPatientResponse en List<DossierMedicalDTO>
     */
    public List<DossierMedicalDTO> obtenirDossiersResponseToDto(ObtenirDossiersParPatientResponse response) {
        if (response == null) {
            return List.of();
        }
        return soapDossierMedicalListToDtoList(response.getDossiersMedicaux());
    }
}