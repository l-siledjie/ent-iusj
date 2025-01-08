package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.DonsDto;

import java.util.List;

public interface DonsService {
    DonsDto save(DonsDto dto);
    DonsDto findById(Integer id);
    DonsDto findByNom(String nom);
    List<DonsDto> findAll();
    void delete(Integer id);
}
