package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OuvrageRepository extends JpaRepository<Ouvrage, Integer> {

//    @Override
    Optional<Ouvrage> findById(Integer integer);
}
