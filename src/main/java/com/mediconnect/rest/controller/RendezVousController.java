package com.mediconnect.rest.controller;

import com.mediconnect.rest.dto.RendezVousDTO;
import com.mediconnect.rest.service.RendezVousService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour les rendez-vous
 * Chemin: src/main/java/com/mediconnect/rest/controller/RendezVousController.java
 */
@RestController
@RequestMapping("/api/rendez-vous")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class RendezVousController {

    private final RendezVousService rendezVousService;

    /**
     * POST /api/rendez-vous
     * Créer un nouveau rendez-vous
     */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'PATIENT')")
    public ResponseEntity<RendezVousDTO> creerRendezVous(@Valid @RequestBody RendezVousDTO rendezVousDTO) {
        RendezVousDTO created = rendezVousService.creerRendezVous(rendezVousDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/rendez-vous/medecin/{medecinId}
     * Lister les rendez-vous d'un médecin
     */
    @GetMapping("/medecin/{medecinId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN')")
    public ResponseEntity<List<RendezVousDTO>> listerRendezVousParMedecin(@PathVariable Long medecinId) {
        List<RendezVousDTO> rendezVous = rendezVousService.listerRendezVousParMedecin(medecinId);
        return ResponseEntity.ok(rendezVous);
    }
}

/**
 * EXEMPLES D'UTILISATION:
 *
 * 1. Créer un rendez-vous (POST /api/rendez-vous):
 * Headers: Authorization: Bearer {token}
 *
 * Request:
 * {
 *   "patientId": 1,
 *   "medecinId": 2,
 *   "dateRendezVous": "2024-12-15T14:30:00",
 *   "motif": "Consultation de suivi",
 *   "statut": "PLANIFIE"
 * }
 *
 * 2. Lister les rendez-vous d'un médecin (GET /api/rendez-vous/medecin/2):
 * Headers: Authorization: Bearer {token}
 */