package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.LigneVenteDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeLigneVente;

//@Api("LigneVentes")
public interface LigneVenteApi {
    @PostMapping(value = routeLigneVente + "/create")
    LigneVenteDto save(@RequestBody LigneVenteDto dto);
    @GetMapping(value = routeLigneVente + "/all")
    List<LigneVenteDto> findAll();
    @GetMapping(value = routeLigneVente + "/{idDon}")
    LigneVenteDto findById(@PathVariable("idDon") Integer id);
    @DeleteMapping( value = routeLigneVente + "/delete/{idDon}")
    void delete(@PathVariable("idDon") Integer id);
}
