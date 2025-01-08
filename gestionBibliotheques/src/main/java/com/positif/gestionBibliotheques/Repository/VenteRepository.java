package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.Vente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenteRepository extends JpaRepository<Vente, Integer> {
}
