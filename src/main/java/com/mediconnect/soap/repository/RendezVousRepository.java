package com.mediconnect.soap.repository;

import com.mediconnect.soap.entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour la gestion des Rendez-vous
 * Chemin: src/main/java/com/mediconnect/soap/repository/RendezVousRepository.java
 */
@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {

    List<RendezVous> findByPatientId(Long patientId);

    List<RendezVous> findByMedecinId(Long medecinId);

    List<RendezVous> findByStatut(String statut);
}