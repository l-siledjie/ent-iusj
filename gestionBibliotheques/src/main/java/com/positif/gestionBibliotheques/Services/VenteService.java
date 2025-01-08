package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.VenteDto;

import java.util.List;

public interface VenteService {
    VenteDto save(VenteDto dto);
    VenteDto findById(Integer id);
    VenteDto findByNom(String nom);
    List<VenteDto> findAll();
    void delete(Integer id);
}
