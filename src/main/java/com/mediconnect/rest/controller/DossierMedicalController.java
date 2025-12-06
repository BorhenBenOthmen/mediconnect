package com.mediconnect.rest.controller;

import com.mediconnect.rest.dto.DossierMedicalDTO;
import com.mediconnect.rest.service.DossierMedicalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour les dossiers médicaux
 * Chemin: src/main/java/com/mediconnect/rest/controller/DossierMedicalController.java
 */
@RestController
@RequestMapping("/api/dossiers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DossierMedicalController {

    private final DossierMedicalService dossierMedicalService;

    /**
     * POST /api/dossiers
     * Créer un nouveau dossier médical
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<DossierMedicalDTO> creerDossierMedical(@Valid @RequestBody DossierMedicalDTO dossierDTO) {
        DossierMedicalDTO created = dossierMedicalService.creerDossierMedical(dossierDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/dossiers/patient/{patientId}
     * Obtenir les dossiers médicaux d'un patient
     */
    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<List<DossierMedicalDTO>> obtenirDossiersParPatient(@PathVariable Long patientId) {
        List<DossierMedicalDTO> dossiers = dossierMedicalService.obtenirDossiersParPatient(patientId);
        return ResponseEntity.ok(dossiers);
    }
}

/**
 * EXEMPLES D'UTILISATION:
 *
 * 1. Créer un dossier médical (POST /api/dossiers):
 * Headers: Authorization: Bearer {token}
 *
 * Request:
 * {
 *   "patientId": 1,
 *   "medecinId": 2,
 *   "dateConsultation": "2024-12-04T10:00:00",
 *   "diagnostic": "Hypertension artérielle",
 *   "traitement": "Amlodipine 5mg 1x par jour",
 *   "notes": "Patient à suivre régulièrement"
 * }
 *
 * 2. Obtenir les dossiers d'un patient (GET /api/dossiers/patient/1):
 * Headers: Authorization: Bearer {token}
 */