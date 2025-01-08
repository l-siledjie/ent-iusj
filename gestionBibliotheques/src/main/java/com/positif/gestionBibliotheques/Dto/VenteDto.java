package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.LigneVente;
import com.positif.gestionBibliotheques.Model.Utilisateur;
import com.positif.gestionBibliotheques.Model.Vente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenteDto {
    private Integer id;
    private Instant date;
    @JsonIgnore
    private Utilisateur utilisateur;
    private List<LigneVenteDto> ligneVenteDtos;

    public static VenteDto fromEntity(Vente vente){
        if (vente.equals(null)){
            return null;
        }

        List<LigneVenteDto> ligneVenteDtos1 = new ArrayList<>();
        return VenteDto.builder()
                .id(vente.getId())
                .date(vente.getDate())
                .utilisateur(vente.getUtilisateur())
                .ligneVenteDtos(ligneVenteDtos1)
                .build();
    }

    public static Vente toEntity(VenteDto dto){
        Vente vente = new Vente();
        vente.setId(dto.getId());
        vente.setDate(dto.getDate());
        vente.setUtilisateur(dto.getUtilisateur());
        List<LigneVente> ligneVentes = new ArrayList<>();
        vente.setLigneVentes(ligneVentes);
        return vente;
    }
}
