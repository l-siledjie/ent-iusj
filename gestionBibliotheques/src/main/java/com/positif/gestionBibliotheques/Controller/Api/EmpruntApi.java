package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.EmpruntDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeEmprunt;

@Api("Emprunts")
public interface EmpruntApi {
    @PostMapping(value = routeEmprunt + "/create")
    EmpruntDto save(@RequestBody EmpruntDto dto);
    @GetMapping(value = routeEmprunt + "/all")
    List<EmpruntDto> findAll();
    @GetMapping(value = routeEmprunt + "/{idEmprunt}")
    EmpruntDto findById(@PathVariable("idEmprunt") Integer id);
    @DeleteMapping( value = routeEmprunt + "/delete/{idEmprunt}")
    void delete(@PathVariable("idEmprunt") Integer id);
}
