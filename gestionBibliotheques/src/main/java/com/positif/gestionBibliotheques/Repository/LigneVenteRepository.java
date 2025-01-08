package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.LigneVente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {
}
