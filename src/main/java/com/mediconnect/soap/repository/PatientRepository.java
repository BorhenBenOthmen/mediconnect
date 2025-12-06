package com.mediconnect.soap.repository;

import com.mediconnect.soap.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository pour la gestion des Patients
 * Chemin: src/main/java/com/mediconnect/soap/repository/PatientRepository.java
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findByEmail(String email);

    Optional<Patient> findByNumeroSecuriteSociale(String numeroSecuriteSociale);
}