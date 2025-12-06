package com.mediconnect.soap.service;

import com.mediconnect.soap.entity.RendezVous;
import com.mediconnect.soap.repository.RendezVousRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service de gestion des rendez-vous
 * Chemin: src/main/java/com/mediconnect/soap/service/RendezVousService.java
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RendezVousService {

    private final RendezVousRepository rendezVousRepository;
    private final PatientService patientService;
    private final MedecinService medecinService;

    /**
     * Créer un nouveau rendez-vous
     */
    public RendezVous creerRendezVous(RendezVous rendezVous) {
        // Vérifier que le patient existe
        patientService.obtenirPatient(rendezVous.getPatientId());

        // Vérifier que le médecin existe
        medecinService.obtenirMedecin(rendezVous.getMedecinId());

        // Par défaut, le statut est PLANIFIE
        if (rendezVous.getStatut() == null || rendezVous.getStatut().isEmpty()) {
            rendezVous.setStatut("PLANIFIE");
        }

        return rendezVousRepository.save(rendezVous);
    }

    /**
     * Obtenir un rendez-vous par ID
     */
    @Transactional(readOnly = true)
    public RendezVous obtenirRendezVous(Long id) {
        return rendezVousRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rendez-vous non trouvé avec l'ID: " + id));
    }

    /**
     * Lister les rendez-vous d'un patient
     */
    @Transactional(readOnly = true)
    public List<RendezVous> listerRendezVousParPatient(Long patientId) {
        return rendezVousRepository.findByPatientId(patientId);
    }

    /**
     * Lister les rendez-vous d'un médecin
     */
    @Transactional(readOnly = true)
    public List<RendezVous> listerRendezVousParMedecin(Long medecinId) {
        return rendezVousRepository.findByMedecinId(medecinId);
    }

    /**
     * Lister les rendez-vous par statut
     */
    @Transactional(readOnly = true)
    public List<RendezVous> listerRendezVousParStatut(String statut) {
        return rendezVousRepository.findByStatut(statut);
    }

    /**
     * Mettre à jour un rendez-vous
     */
    public RendezVous mettreAJourRendezVous(Long id, RendezVous rendezVousDetails) {
        RendezVous rendezVous = obtenirRendezVous(id);

        rendezVous.setDateRendezVous(rendezVousDetails.getDateRendezVous());
        rendezVous.setMotif(rendezVousDetails.getMotif());
        rendezVous.setStatut(rendezVousDetails.getStatut());

        return rendezVousRepository.save(rendezVous);
    }

    /**
     * Annuler un rendez-vous
     */
    public RendezVous annulerRendezVous(Long id) {
        RendezVous rendezVous = obtenirRendezVous(id);
        rendezVous.setStatut("ANNULE");
        return rendezVousRepository.save(rendezVous);
    }

    /**
     * Supprimer un rendez-vous
     */
    public void supprimerRendezVous(Long id) {
        RendezVous rendezVous = obtenirRendezVous(id);
        rendezVousRepository.delete(rendezVous);
    }
}