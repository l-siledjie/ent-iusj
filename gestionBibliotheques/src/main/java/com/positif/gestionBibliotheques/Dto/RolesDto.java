package com.positif.gestionBibliotheques.Dto;

import com.positif.gestionBibliotheques.Model.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {
    private Integer id;
    private String nom;
    public static RolesDto fromEntity(Roles roles){
        if (roles.equals(null)){
            return null;
        }
        return RolesDto.builder()
                .id(roles.getId())
                .nom(roles.getNom())
                .build();
    }

    public static Roles toEntity(RolesDto dto){
        if (dto == null){
            return null;
        }
        Roles role = new Roles();
        role.setId(dto.getId());
        role.setNom(dto.getNom());

        return role;
    }
}
