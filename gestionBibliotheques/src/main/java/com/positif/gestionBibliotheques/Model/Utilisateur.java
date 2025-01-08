package com.positif.gestionBibliotheques.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Utilisateur")
public class Utilisateur extends SuperEntity{
    private String nom;
    private String email;
    private String login;
    private String passWord;
    private String photo;
    @ManyToOne
    @JoinColumn(name = "idRole")
    private Roles roles;
    @OneToMany(mappedBy = "utilisateur")
    private List<Dons> dons;
    @OneToMany(mappedBy = "utilisateur")
    private List<Vente> ventes;
    @OneToMany(mappedBy = "utilisateur")
    private List<Emprunt> emprunts;
}
