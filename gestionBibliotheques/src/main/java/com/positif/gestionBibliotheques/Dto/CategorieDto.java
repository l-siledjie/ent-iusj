package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.Categorie;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategorieDto {
    private Integer id;
    private String code;
    private String nom;
    private String description;
    private String photo;
    @JsonIgnore
    private List<OuvrageDto> ouvrageDtos;
    public static CategorieDto fromEntity(Categorie categorie){
        if (categorie.equals(null)) {
            return null;
        }

        return CategorieDto.builder()
                .id(categorie.getId())
                .code(categorie.getCode())
                .nom(categorie.getNom())
                .photo(categorie.getPhoto())
                .description(categorie.getDescription())
                .build();
    }

    public static Categorie toEntity(CategorieDto dto){
        if (dto.equals(null)){
            return null;
        }
        Categorie categorie = new Categorie();
        categorie.setId(dto.getId());
        categorie.setCode(dto.getCode());
        categorie.setNom(dto.getNom());
        categorie.setPhoto(dto.getPhoto());
        categorie.setDescription(dto.getDescription());

        return categorie;
    }

}