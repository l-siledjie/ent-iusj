package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.CategorieDto;

import java.util.List;

public interface CategorieService {

    CategorieDto save(CategorieDto dto);
    CategorieDto findById(Integer id);
    CategorieDto findByNom(String nom);
    List<CategorieDto> findAll();
    void delete(Integer id);

}
