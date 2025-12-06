package com.mediconnect.soap.service;

import com.mediconnect.soap.entity.Medecin;
import com.mediconnect.soap.repository.MedecinRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service de gestion des médecins
 * Chemin: src/main/java/com/mediconnect/soap/service/MedecinService.java
 */
@Service
@RequiredArgsConstructor
@Transactional
public class MedecinService {

    private final MedecinRepository medecinRepository;

    /**
     * Créer un nouveau médecin
     */
    public Medecin creerMedecin(Medecin medecin) {
        // Vérifier si l'email existe déjà
        if (medecinRepository.findByEmail(medecin.getEmail()).isPresent()) {
            throw new RuntimeException("Un médecin avec cet email existe déjà");
        }

        // Vérifier si le numéro d'ordre existe déjà
        if (medecinRepository.findByNumeroOrdre(medecin.getNumeroOrdre()).isPresent()) {
            throw new RuntimeException("Un médecin avec ce numéro d'ordre existe déjà");
        }

        return medecinRepository.save(medecin);
    }

    /**
     * Obtenir un médecin par ID
     */
    @Transactional(readOnly = true)
    public Medecin obtenirMedecin(Long id) {
        return medecinRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Médecin non trouvé avec l'ID: " + id));
    }

    /**
     * Lister tous les médecins
     */
    @Transactional(readOnly = true)
    public List<Medecin> listerMedecins() {
        return medecinRepository.findAll();
    }

    /**
     * Lister les médecins par spécialité
     */
    @Transactional(readOnly = true)
    public List<Medecin> listerMedecinsParSpecialite(String specialite) {
        return medecinRepository.findBySpecialite(specialite);
    }

    /**
     * Mettre à jour un médecin
     */
    public Medecin mettreAJourMedecin(Long id, Medecin medecinDetails) {
        Medecin medecin = obtenirMedecin(id);

        medecin.setNom(medecinDetails.getNom());
        medecin.setPrenom(medecinDetails.getPrenom());
        medecin.setSpecialite(medecinDetails.getSpecialite());
        medecin.setTelephone(medecinDetails.getTelephone());

        return medecinRepository.save(medecin);
    }

    /**
     * Supprimer un médecin
     */
    public void supprimerMedecin(Long id) {
        Medecin medecin = obtenirMedecin(id);
        medecinRepository.delete(medecin);
    }
}