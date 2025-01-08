package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.LocalisationDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeLocalisation;

@Api("Localisation")
public interface LocalisationApi {
    @PostMapping(value = routeLocalisation + "/create")
    LocalisationDto save(@RequestBody LocalisationDto dto);
    @GetMapping(value = routeLocalisation + "/all")
    List<LocalisationDto> findAll();
    @GetMapping(value = routeLocalisation + "/{idLocalisation}")
    LocalisationDto findById(@PathVariable("idLocalisation") Integer id);
    @DeleteMapping( value = routeLocalisation + "/delete/{idLocalisation}")
    void delete(@PathVariable("idLocalisation") Integer id);
}
