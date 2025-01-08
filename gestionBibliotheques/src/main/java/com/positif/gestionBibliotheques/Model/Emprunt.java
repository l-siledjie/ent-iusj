package com.positif.gestionBibliotheques.Model;

import com.positif.gestionBibliotheques.Model.SuperEntity;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Emprunt")
public class Emprunt extends SuperEntity {
    private String motif;
    private Instant dateDebut;
    private Instant dateFin;
    private Instant dateRestitution;
    private boolean etat;
    @ManyToOne
    @JoinColumn(name = "idUtilisateur")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "idOuvrage")
    private Ouvrage ouvrage;

}
