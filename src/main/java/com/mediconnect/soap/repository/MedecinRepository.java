package com.mediconnect.soap.repository;

import com.mediconnect.soap.entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository pour la gestion des Médecins
 * Chemin: src/main/java/com/mediconnect/soap/repository/MedecinRepository.java
 */
@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {

    Optional<Medecin> findByEmail(String email);

    Optional<Medecin> findByNumeroOrdre(String numeroOrdre);

    List<Medecin> findBySpecialite(String specialite);
}