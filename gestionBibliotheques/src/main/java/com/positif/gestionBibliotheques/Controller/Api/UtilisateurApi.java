package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeUtilisateur;

@Api("Utilisateurs")
public interface UtilisateurApi {
    @PostMapping(value = routeUtilisateur + "/create")
    UtilisateurDto save(@RequestBody UtilisateurDto dto);
    @GetMapping(value = routeUtilisateur + "/all")
    List<UtilisateurDto> findAll();
    @GetMapping(value = routeUtilisateur + "/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);
    @GetMapping(value = routeUtilisateur + "/byEmail/{emailUser}")
    UtilisateurDto findByEmail(@PathVariable("emailUser") String email);
    @DeleteMapping( value = routeUtilisateur + "/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);
}
