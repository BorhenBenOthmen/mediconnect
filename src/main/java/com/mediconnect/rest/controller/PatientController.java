package com.mediconnect.rest.controller;

import com.mediconnect.rest.dto.PatientDTO;
import com.mediconnect.rest.service.PatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour les patients
 * Chemin: src/main/java/com/mediconnect/rest/controller/PatientController.java
 */
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    /**
     * POST /api/patients
     * Créer un nouveau patient
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<PatientDTO> creerPatient(@Valid @RequestBody PatientDTO patientDTO) {
        PatientDTO created = patientService.creerPatient(patientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/patients/{id}
     * Obtenir un patient par ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'PATIENT')")
    public ResponseEntity<PatientDTO> obtenirPatient(@PathVariable Long id) {
        PatientDTO patient = patientService.obtenirPatient(id);
        return ResponseEntity.ok(patient);
    }

    /**
     * GET /api/patients
     * Lister tous les patients
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<List<PatientDTO>> listerPatients() {
        List<PatientDTO> patients = patientService.listerPatients();
        return ResponseEntity.ok(patients);
    }
}

/**
 * EXEMPLES D'UTILISATION:
 *
 * 1. Créer un patient (POST /api/patients):
 * Headers: Authorization: Bearer {token}
 *
 * Request:
 * {
 *   "nom": "Dupont",
 *   "prenom": "Jean",
 *   "dateNaissance": "1990-05-15",
 *   "telephone": "0612345678",
 *   "email": "jean.dupont@email.com",
 *   "adresse": "123 Rue de Paris, 75001 Paris",
 *   "numeroSecuriteSociale": "190055012345678"
 * }
 *
 * 2. Obtenir un patient (GET /api/patients/1):
 * Headers: Authorization: Bearer {token}
 *
 * 3. Lister les patients (GET /api/patients):
 * Headers: Authorization: Bearer {token}
 */