package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtilisateurDto {
    private Integer id;
    private String nom;
    private String email;
    private String login;
    private String passWord;
    private String photo;
//    @JsonIgnore
    private Roles roles;
    @JsonIgnore
    private List<DonsDto> donsDtos;
    @JsonIgnore
    private List<VenteDto> venteDtos;
    @JsonIgnore
    private List<EmpruntDto> empruntDtos;

    public static UtilisateurDto fromEntity(Utilisateur utilisateur){
        if(utilisateur.equals(null)){
            return null;
        }
        List<EmpruntDto> empruntDtos = new ArrayList<>();
        List<VenteDto> venteDtos1 = new ArrayList<>();
        List<DonsDto> donsDtos1 = new ArrayList<>();
        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .nom(utilisateur.getNom())
                .email(utilisateur.getEmail())
                .login(utilisateur.getLogin())
                .passWord(utilisateur.getPassWord())
                .photo(utilisateur.getPhoto())
                .roles(utilisateur.getRoles())
                .empruntDtos(empruntDtos)
                .venteDtos(venteDtos1)
                .donsDtos(donsDtos1)
                .build();
    }

    public static Utilisateur toEntity(UtilisateurDto dto){
        if (dto.equals(null)){
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(dto.getId());
        utilisateur.setNom(dto.getNom());
        utilisateur.setEmail(dto.getEmail());
        utilisateur.setLogin(dto.getLogin());
        utilisateur.setPassWord(dto.getPassWord());
        utilisateur.setPhoto(dto.getPhoto());
        utilisateur.setRoles(dto.getRoles());
        List<Emprunt> emprunts1 = new ArrayList<>();
        utilisateur.setEmprunts(emprunts1);
        List<Vente> ventes = new ArrayList<>();
        utilisateur.setVentes(ventes);
        List<Dons> dons = new ArrayList<>();
        utilisateur.setDons(dons);
        return utilisateur;
    }
}
