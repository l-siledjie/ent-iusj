package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.Ouvrage;
import com.positif.gestionBibliotheques.Model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpruntDto1 {
    private Integer id;
    private String motif;
    private Instant dateDebut;
    private Instant dateFin;
    private Instant dateRestitution;
    private boolean etat;
    @JsonIgnore
    private Utilisateur utilisateur;
    //    @JsonIgnore
    private Integer ouvrage;
}
