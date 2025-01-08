package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.LigneVente;
import com.positif.gestionBibliotheques.Model.Ouvrage;
import com.positif.gestionBibliotheques.Model.Vente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneVenteDto {
    private Integer id;
    private Double quantite;
    private Double prixTotal;
    @JsonIgnore
    private Ouvrage ouvrage;
    @JsonIgnore
    private Vente vente;

    public static LigneVenteDto fromEntity(LigneVente ligneVente){
        if (ligneVente.equals(null)){
            return null;
        }
        return LigneVenteDto.builder()
                .id(ligneVente.getId())
                .quantite(ligneVente.getQuantite())
                .prixTotal(ligneVente.getPrixTotal())
                .ouvrage(ligneVente.getOuvrage())
                .vente(ligneVente.getVente())
                .build();
    }

    public static LigneVente toEntity(LigneVenteDto dto){
        if (dto.equals(null)){
            return null;
        }

        LigneVente ligneVente = new LigneVente();
        ligneVente.setId(dto.getId());
        ligneVente.setQuantite(dto.getQuantite());
        ligneVente.setPrixTotal(dto.getPrixTotal());
        ligneVente.setOuvrage(dto.getOuvrage());
        ligneVente.setVente(dto.getVente());

        return ligneVente;
    }

}
