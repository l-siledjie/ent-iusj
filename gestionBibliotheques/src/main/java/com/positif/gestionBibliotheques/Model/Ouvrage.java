package com.positif.gestionBibliotheques.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Ouvrage")
public class Ouvrage extends SuperEntity{
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
    @OneToMany(mappedBy = "ouvrage")
    private List<Emprunt> emprunts;
    @OneToMany(mappedBy = "ouvrage")
    private List<LigneVente> ligneVentes;
    @ManyToOne
    @JoinColumn(name = "idCategorie")
    private Categorie categorie;
    @OneToMany(mappedBy = "ouvrage")
    private List<Favoris> favoris;
    @ManyToOne
    @JoinColumn(name = "codeLocalisation")
    private Localisation localisation;
}
