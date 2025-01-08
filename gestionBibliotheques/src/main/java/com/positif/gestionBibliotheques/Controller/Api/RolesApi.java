package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.RolesDto;
import com.positif.gestionBibliotheques.Dto.RolesDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeRoles;

@Api("Roles")
public interface RolesApi {
    @PostMapping(value = routeRoles + "/create")
    RolesDto save(@RequestBody RolesDto dto);
    @GetMapping(value = routeRoles + "/all")
    List<RolesDto> findAll();
    @GetMapping(value = routeRoles + "/{idRole}")
    RolesDto findById(@PathVariable("idRole") Integer id);
    @DeleteMapping( value = routeRoles + "/delete/{idRole}")
    void delete(@PathVariable("idRole") Integer id);
}
