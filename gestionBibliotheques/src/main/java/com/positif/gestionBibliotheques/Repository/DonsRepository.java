package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.Dons;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.Repository;

public interface DonsRepository extends JpaRepository<Dons, Integer> {
}
