package com.positif.gestionBibliotheques.Repository;

import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}
