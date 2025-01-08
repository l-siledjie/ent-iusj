package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.RolesDto;

import java.util.List;

public interface RolesService {
    RolesDto save(RolesDto dto);
    RolesDto findById(Integer id);
    RolesDto findByNom(String nom);
    List<RolesDto> findAll();
    void delete(Integer id);
}
