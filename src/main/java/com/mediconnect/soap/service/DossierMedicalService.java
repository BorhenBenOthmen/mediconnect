package com.mediconnect.soap.service;

import com.mediconnect.soap.entity.DossierMedical;
import com.mediconnect.soap.repository.DossierMedicalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service de gestion des dossiers médicaux
 */
@Service
@RequiredArgsConstructor
@Transactional
public class DossierMedicalService {

    private final DossierMedicalRepository dossierMedicalRepository;
    private final PatientService patientService;
    private final MedecinService medecinService;

    /**
     * Créer un nouveau dossier médical
     */
    public DossierMedical creerDossierMedical(DossierMedical dossierMedical) {
        // Vérifier que le patient existe
        patientService.obtenirPatient(dossierMedical.getPatientId());

        // Vérifier que le médecin existe
        medecinService.obtenirMedecin(dossierMedical.getMedecinId());

        return dossierMedicalRepository.save(dossierMedical);
    }

    /**
     * Obtenir un dossier médical par ID
     */
    @Transactional(readOnly = true)
    public DossierMedical obtenirDossierMedical(Long id) {
        return dossierMedicalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier médical non trouvé avec l'ID: " + id));
    }

    /**
     * Lister les dossiers médicaux d'un patient
     */
    @Transactional(readOnly = true)
    public List<DossierMedical> obtenirDossiersParPatient(Long patientId) {
        // Vérifier que le patient existe
        patientService.obtenirPatient(patientId);
        return dossierMedicalRepository.findByPatientId(patientId);
    }

    /**
     * Lister les dossiers médicaux d'un médecin
     */
    @Transactional(readOnly = true)
    public List<DossierMedical> obtenirDossiersParMedecin(Long medecinId) {
        // Vérifier que le médecin existe
        medecinService.obtenirMedecin(medecinId);
        return dossierMedicalRepository.findByMedecinId(medecinId);
    }

    /**
     * Mettre à jour un dossier médical
     */
    public DossierMedical mettreAJourDossierMedical(Long id, DossierMedical dossierDetails) {
        DossierMedical dossier = obtenirDossierMedical(id);

        dossier.setDiagnostic(dossierDetails.getDiagnostic());
        dossier.setTraitement(dossierDetails.getTraitement());
        dossier.setNotes(dossierDetails.getNotes());

        return dossierMedicalRepository.save(dossier);
    }

    /**
     * Supprimer un dossier médical
     */
    public void supprimerDossierMedical(Long id) {
        DossierMedical dossier = obtenirDossierMedical(id);
        dossierMedicalRepository.delete(dossier);
    }
}