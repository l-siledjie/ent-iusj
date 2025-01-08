package com.positif.gestionBibliotheques.Model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LigneVente")
public class LigneVente extends SuperEntity{
    private Double quantite;
    private Double prixTotal;
    @ManyToOne
    @JoinColumn(name = "idOuvrage")
    private Ouvrage ouvrage;
    @ManyToOne
    @JoinColumn(name = "idVente")
    private Vente vente;
}
