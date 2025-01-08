package com.positif.gestionBibliotheques.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.positif.gestionBibliotheques.Model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OuvrageDto {
    private Integer id;
    private String nom;
    private String isbn;
    private String auteur;
    private String maison_Edition;
    private String description;
    private String nature;
    private Double prixUnitaire;
    private String photo;
    private String fichier;
    private String type;
    private String genre;
    private boolean disponible;
    private LocalisationDto localisationDto;
    @JsonIgnore
    private List<EmpruntDto> emprunts;
    @JsonIgnore
    private List<FavorisDto> favorisDtos;
    @JsonIgnore
    private List<LigneVenteDto> ligneVentes;
    private CategorieDto categorie;

    public static OuvrageDto fromEntity(Ouvrage ouvrage){
        if(ouvrage.equals(null)){
            return null;
        }
        List<EmpruntDto> empruntDtos = new ArrayList<>();
        List<LigneVenteDto> ligneVenteDtos = new ArrayList<>();
        List<FavorisDto> favorisDtos1 = new ArrayList<>();
        return OuvrageDto.builder()
                .id(ouvrage.getId())
                .isbn(ouvrage.getIsbn())
                .nom(ouvrage.getNom())
                .auteur(ouvrage.getAuteur())
                .maison_Edition(ouvrage.getMaison_Edition())
                .description(ouvrage.getDescription())
                .nature(ouvrage.getNature())
                .prixUnitaire(ouvrage.getPrixUnitaire())
                .photo(ouvrage.getPhoto())
                .fichier(ouvrage.getFichier())
                .type(ouvrage.getType())
                .genre(ouvrage.getGenre())
                .disponible(ouvrage.isDisponible())
                .categorie(CategorieDto.fromEntity(ouvrage.getCategorie()))
                .emprunts(empruntDtos)
                .ligneVentes(ligneVenteDtos)
                .favorisDtos(favorisDtos1)
                .build();
    }

    public static Ouvrage toEntity(OuvrageDto dto){
        if (dto.equals(null)){
            return null;
        }

        Ouvrage ouvrage = new Ouvrage();
        ouvrage.setId(dto.getId());
        ouvrage.setIsbn(dto.getIsbn());
        ouvrage.setNom(dto.getNom());
        ouvrage.setAuteur(dto.getAuteur());
        ouvrage.setDescription(dto.getDescription());
        ouvrage.setNature(dto.getNature());
        ouvrage.setMaison_Edition(dto.getMaison_Edition());
        ouvrage.setPrixUnitaire(dto.getPrixUnitaire());
        ouvrage.setPhoto(dto.getPhoto());
        ouvrage.setFichier(dto.getFichier());
        ouvrage.setDisponible(dto.isDisponible());
        ouvrage.setType(dto.getType());
        ouvrage.setGenre(dto.getGenre());
        ouvrage.setCategorie(CategorieDto.toEntity(dto.getCategorie()));
        List<Emprunt> emprunts1 = new ArrayList<>();
        ouvrage.setEmprunts(emprunts1);
        List<LigneVente> ligneVentes1 = new ArrayList<>();
        ouvrage.setLigneVentes(ligneVentes1);
        List<Favoris> favoris = new ArrayList<>();
        ouvrage.setFavoris(favoris);
        return ouvrage;
    }
}
