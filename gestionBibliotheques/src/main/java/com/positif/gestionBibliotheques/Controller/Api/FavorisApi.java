package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.FavorisDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeOuvrageFav;

@Api("Favoris")
public interface FavorisApi {

    @PostMapping(value = routeOuvrageFav + "/create")
    FavorisDto save(@RequestBody FavorisDto dto);
    @GetMapping(value = routeOuvrageFav + "/all")
    List<FavorisDto> findAll();
    @GetMapping(value = routeOuvrageFav + "/all/{idUser}")
    List<FavorisDto> findAllByUtilisateurId(@PathVariable("idUser") Integer id);
    @GetMapping(value = routeOuvrageFav + "/{idFavoris}")
    FavorisDto findById(@PathVariable("idFavoris") Integer id);
    @DeleteMapping( value = routeOuvrageFav + "/delete/{idFavoris}")
    void delete(@PathVariable("idFavoris") Integer id);

}
