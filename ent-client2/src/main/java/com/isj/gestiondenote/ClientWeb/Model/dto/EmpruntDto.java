package com.isj.gestiondenote.ClientWeb.Model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
//import com.positif.gestionBibliotheques.Model.Emprunt;
//import com.positif.gestionBibliotheques.Model.Ouvrage;
//import com.positif.gestionBibliotheques.Model.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpruntDto {
    private Integer id;
    private String motif;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateRestitution;
    private boolean etat;
    @JsonIgnore
    private UserDto utilisateur;
    @JsonIgnore
    private Integer ouvrage;

//    public static EmpruntDto fromEntity(Emprunt emprunt){
//        if (emprunt.equals(null)){
//            return null;
//        }
//
//        return EmpruntDto.builder()
//                .id(emprunt.getId())
//                .motif(emprunt.getMotif())
//                .dateDebut(emprunt.getDateDebut())
//                .dateFin(emprunt.getDateFin())
//                .dateRestitution(emprunt.getDateRestitution())
//                .etat(emprunt.isEtat())
//                .utilisateur(emprunt.getUtilisateur())
//                .ouvrage(emprunt.getOuvrage())
//                .build();
//    }
//
//    public static Emprunt toEntity(EmpruntDto dto){
//        if (dto.equals(null)){
//            return null;
//        }
//
//        Emprunt emprunt = new Emprunt();
//        emprunt.setId(dto.getId());
//        emprunt.setMotif(dto.getMotif());
//        emprunt.setDateDebut(dto.getDateDebut());
//        emprunt.setDateFin(dto.getDateFin());
//        emprunt.setDateRestitution(dto.getDateRestitution());
//        emprunt.setEtat(dto.isEtat());
//        emprunt.setUtilisateur(dto.getUtilisateur());
//        emprunt.setOuvrage(dto.getOuvrage());
//        return  emprunt;
//
//    }
}
