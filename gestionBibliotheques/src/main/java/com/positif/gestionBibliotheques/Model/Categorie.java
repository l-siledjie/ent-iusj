package com.positif.gestionBibliotheques.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Categorie")
public class Categorie extends SuperEntity{
    private String code;
    private String nom;
    private String description;
    private String photo;
    @OneToMany(mappedBy = "categorie")
    private List<Ouvrage> ouvrages;
}
