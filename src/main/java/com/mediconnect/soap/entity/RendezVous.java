package com.mediconnect.soap.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entité Rendez-vous - Représente un rendez-vous médical
 * Chemin: src/main/java/com/mediconnect/soap/entity/RendezVous.java
 */
@Entity
@Table(name = "rendez_vous")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long patientId;

    @Column(nullable = false)
    private Long medecinId;

    @Column(nullable = false)
    private LocalDateTime dateRendezVous;

    @Column(nullable = false, length = 500)
    private String motif;

    @Column(nullable = false, length = 20)
    private String statut; // PLANIFIE, CONFIRME, ANNULE, TERMINE

    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation = LocalDateTime.now();
}