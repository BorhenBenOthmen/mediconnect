package com.mediconnect.soap.repository;

import com.mediconnect.soap.entity.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pour la gestion des Dossiers Médicaux
 * Chemin: src/main/java/com/mediconnect/soap/repository/DossierMedicalRepository.java
 */
@Repository
public interface DossierMedicalRepository extends JpaRepository<DossierMedical, Long> {

    List<DossierMedical> findByPatientId(Long patientId);

    List<DossierMedical> findByMedecinId(Long medecinId);
}