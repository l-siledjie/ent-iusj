package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.LocalisationDto;

import java.util.List;

public interface LocalisationService {
    LocalisationDto save(LocalisationDto dto);
    LocalisationDto findById(Integer id);
//    LocalisationDto findByCode(String code);
    List<LocalisationDto> findAll();
    void delete(Integer id);
}
