package com.positif.gestionBibliotheques.Model;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Vente")
public class Vente extends SuperEntity{
    private Instant date;
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
    @OneToMany(mappedBy = "vente")
    private List<LigneVente> ligneVentes;
}
