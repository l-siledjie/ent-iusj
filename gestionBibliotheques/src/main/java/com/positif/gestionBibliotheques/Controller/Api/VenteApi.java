package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.VenteDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeVente;

@Api("Ventes")
public interface VenteApi {
    @PostMapping(value = routeVente + "/create")
    VenteDto save(@RequestBody VenteDto dto);
    @GetMapping(value = routeVente + "/all")
    List<VenteDto> findAll();
    @GetMapping(value = routeVente + "/{idDon}")
    VenteDto findById(@PathVariable("idDon") Integer id);
    @DeleteMapping( value = routeVente + "/delete/{idDon}")
    void delete(@PathVariable("idDon") Integer id);
}
