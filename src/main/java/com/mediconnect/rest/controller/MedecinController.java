package com.mediconnect.rest.controller;

import com.mediconnect.rest.dto.MedecinDTO;
import com.mediconnect.rest.service.MedecinService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST pour les médecins
 * Chemin: src/main/java/com/mediconnect/rest/controller/MedecinController.java
 */
@RestController
@RequestMapping("/api/medecins")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MedecinController {

    private final MedecinService medecinService;

    /**
     * POST /api/medecins
     * Créer un nouveau médecin
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedecinDTO> creerMedecin(@Valid @RequestBody MedecinDTO medecinDTO) {
        MedecinDTO created = medecinService.creerMedecin(medecinDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * GET /api/medecins/{id}
     * Obtenir un médecin par ID
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'PATIENT')")
    public ResponseEntity<MedecinDTO> obtenirMedecin(@PathVariable Long id) {
        MedecinDTO medecin = medecinService.obtenirMedecin(id);
        return ResponseEntity.ok(medecin);
    }

    /**
     * GET /api/medecins
     * Lister tous les médecins
     */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDECIN', 'PATIENT')")
    public ResponseEntity<List<MedecinDTO>> listerMedecins() {
        List<MedecinDTO> medecins = medecinService.listerMedecins();
        return ResponseEntity.ok(medecins);
    }
}

/**
 * EXEMPLES D'UTILISATION:
 *
 * 1. Créer un médecin (POST /api/medecins):
 * Headers: Authorization: Bearer {token}
 *
 * Request:
 * {
 *   "nom": "Martin",
 *   "prenom": "Sophie",
 *   "specialite": "Cardiologie",
 *   "telephone": "0612345678",
 *   "email": "sophie.martin@hospital.com",
 *   "numeroOrdre": "123456789"
 * }
 *
 * 2. Obtenir un médecin (GET /api/medecins/1):
 * Headers: Authorization: Bearer {token}
 *
 * 3. Lister les médecins (GET /api/medecins):
 * Headers: Authorization: Bearer {token}
 */