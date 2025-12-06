package com.mediconnect.soap.service;

import com.mediconnect.soap.entity.Patient;
import com.mediconnect.soap.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service de gestion des patients
 */
@Service
@RequiredArgsConstructor
@Transactional
public class PatientService {

    private final PatientRepository patientRepository;

    /**
     * Créer un nouveau patient
     */
    public Patient creerPatient(Patient patient) {
        // Vérifier si l'email existe déjà
        if (patientRepository.findByEmail(patient.getEmail()).isPresent()) {
            throw new RuntimeException("Un patient avec cet email existe déjà");
        }

        // Vérifier si le numéro de sécurité sociale existe déjà
        if (patientRepository.findByNumeroSecuriteSociale(patient.getNumeroSecuriteSociale()).isPresent()) {
            throw new RuntimeException("Un patient avec ce numéro de sécurité sociale existe déjà");
        }

        return patientRepository.save(patient);
    }

    /**
     * Obtenir un patient par ID
     */
    @Transactional(readOnly = true)
    public Patient obtenirPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient non trouvé avec l'ID: " + id));
    }

    /**
     * Lister tous les patients
     */
    @Transactional(readOnly = true)
    public List<Patient> listerPatients() {
        return patientRepository.findAll();
    }

    /**
     * Mettre à jour un patient
     */
    public Patient mettreAJourPatient(Long id, Patient patientDetails) {
        Patient patient = obtenirPatient(id);

        patient.setNom(patientDetails.getNom());
        patient.setPrenom(patientDetails.getPrenom());
        patient.setDateNaissance(patientDetails.getDateNaissance());
        patient.setTelephone(patientDetails.getTelephone());
        patient.setAdresse(patientDetails.getAdresse());

        return patientRepository.save(patient);
    }

    /**
     * Supprimer un patient
     */
    public void supprimerPatient(Long id) {
        Patient patient = obtenirPatient(id);
        patientRepository.delete(patient);
    }
}