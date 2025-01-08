package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.FavorisDto;

import java.util.List;

public interface FavorisService {

    FavorisDto save(FavorisDto dto);
    FavorisDto findById(Integer id);
    List<FavorisDto> findAll();
    List<FavorisDto> findAllByIdUtilisateur(Integer id);
    void delete(Integer id);

}
