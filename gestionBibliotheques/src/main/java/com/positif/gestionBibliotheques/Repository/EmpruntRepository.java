package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Integer> {
}
