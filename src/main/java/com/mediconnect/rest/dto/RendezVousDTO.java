package com.mediconnect.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * DTO pour Rendez-vous
 * Chemin: src/main/java/com/mediconnect/rest/dto/RendezVousDTO.java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVousDTO {

    private Long id;

    @NotNull(message = "L'ID du patient est obligatoire")
    private Long patientId;

    @NotNull(message = "L'ID du médecin est obligatoire")
    private Long medecinId;

    @NotNull(message = "La date du rendez-vous est obligatoire")
    private LocalDateTime dateRendezVous;

    @NotBlank(message = "Le motif est obligatoire")
    private String motif;

    private String statut; // PLANIFIE, CONFIRME, ANNULE, TERMINE
}