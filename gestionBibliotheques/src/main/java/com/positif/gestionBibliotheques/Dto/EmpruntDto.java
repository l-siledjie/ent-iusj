package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.Emprunt;
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
public class EmpruntDto {
    private Integer id;
    private String motif;
    private Instant dateDebut;
    private Instant dateFin;
    private Instant dateRestitution;
    private boolean etat;
    @JsonIgnore
    private Utilisateur utilisateur;
    @JsonIgnore
    private Ouvrage ouvrage;

    public static EmpruntDto fromEntity(Emprunt emprunt){
        if (emprunt.equals(null)){
            return null;
        }

        return EmpruntDto.builder()
                .id(emprunt.getId())
                .motif(emprunt.getMotif())
                .dateDebut(emprunt.getDateDebut())
                .dateFin(emprunt.getDateFin())
                .dateRestitution(emprunt.getDateRestitution())
                .etat(emprunt.isEtat())
                .utilisateur(emprunt.getUtilisateur())
                .ouvrage(emprunt.getOuvrage())
                .build();
    }

    public static Emprunt toEntity(EmpruntDto dto){
        if (dto.equals(null)){
            return null;
        }

        Emprunt emprunt = new Emprunt();
        emprunt.setId(dto.getId());
        emprunt.setMotif(dto.getMotif());
        emprunt.setDateDebut(dto.getDateDebut());
        emprunt.setDateFin(dto.getDateFin());
        emprunt.setDateRestitution(dto.getDateRestitution());
        emprunt.setEtat(dto.isEtat());
        emprunt.setUtilisateur(dto.getUtilisateur());
        emprunt.setOuvrage(dto.getOuvrage());
        return  emprunt;

    }
}
