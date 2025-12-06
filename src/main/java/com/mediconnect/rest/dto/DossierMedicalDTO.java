package com.mediconnect.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO pour Dossier Médical
 * Chemin: src/main/java/com/mediconnect/rest/dto/DossierMedicalDTO.java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DossierMedicalDTO {

    private Long id;

    @NotNull(message = "L'ID du patient est obligatoire")
    private Long patientId;

    @NotNull(message = "L'ID du médecin est obligatoire")
    private Long medecinId;

    @NotNull(message = "La date de consultation est obligatoire")
    private LocalDateTime dateConsultation;

    @NotBlank(message = "Le diagnostic est obligatoire")
    private String diagnostic;

    @NotBlank(message = "Le traitement est obligatoire")
    private String traitement;

    private String notes;
}