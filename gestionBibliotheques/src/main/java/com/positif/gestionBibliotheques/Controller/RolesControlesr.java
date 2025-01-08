package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.RolesApi;
import com.positif.gestionBibliotheques.Dto.RolesDto;
import com.positif.gestionBibliotheques.Services.RolesService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class RolesControlesr implements RolesApi {
    private RolesService rolesService;
    @Override
    public RolesDto save(RolesDto dto) {
        return rolesService.save(dto);
    }

    @Override
    public List<RolesDto> findAll() {
        return rolesService.findAll();
    }

    @Override
    public RolesDto findById(Integer id) {
        return rolesService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        rolesService.delete(id);
    }
}
