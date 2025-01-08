package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import io.swagger.annotations.Api;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeDons;

@Api("Dons")
public interface DonsApi {
    @PostMapping(value = routeDons + "/create")
    DonsDto save(@RequestBody DonsDto dto);
    @GetMapping(value = routeDons + "/all")
    List<DonsDto> findAll();
    @GetMapping(value = routeDons + "/{idDon}")
    DonsDto findById(@PathVariable("idDon") Integer id);
    @DeleteMapping( value = routeDons + "/delete/{idDon}")
    void delete(@PathVariable("idDon") Integer id);
}
