package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.LigneVenteDto;

import java.util.List;

public interface LigneVenteService {
    LigneVenteDto save(LigneVenteDto dto);
    LigneVenteDto findById(Integer id);
    LigneVenteDto findByNom(String nom);
    List<LigneVenteDto> findAll();
    void delete(Integer id);
}
