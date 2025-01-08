package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.EmpruntDto;

import java.util.List;

public interface EmpruntService {
    EmpruntDto save(EmpruntDto dto);
    EmpruntDto findById(Integer id);
    EmpruntDto findByNom(String nom);
    List<EmpruntDto> findAll();
    void delete(Integer id);
}
