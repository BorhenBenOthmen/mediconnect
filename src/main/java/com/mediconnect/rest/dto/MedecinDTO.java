package com.mediconnect.rest.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO pour Médecin
 * Chemin: src/main/java/com/mediconnect/rest/dto/MedecinDTO.java
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedecinDTO {

    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "La spécialité est obligatoire")
    private String specialite;

    @NotBlank(message = "Le téléphone est obligatoire")
    private String telephone;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le numéro d'ordre est obligatoire")
    private String numeroOrdre;
}