package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Favoris;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavorisRepository extends JpaRepository<Favoris, Integer> {
    List<Favoris> findAllByIdUtilisateur(Integer idUtilisateur);
}
