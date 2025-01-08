package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.CategorieDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.route;

@Api("Categories")
public interface CategorieApi {

    @PostMapping(value = route + "/categories/create")
    @ApiOperation(value="enregistrer une categorie(ajouter/modifier)", notes="Cette methode permet d'enregistrer ou de modifier une categorie", response = CategorieDto.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "L'objet categorie crée / modifié"),
            @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")
    })
    CategorieDto save(@RequestBody CategorieDto dto);

    @GetMapping(value = route + "/categories/{idCat}")
    @ApiOperation(value="Rechercher une categorie par ID", notes="Cette methode permet de rechercher une categorie par son ID", response = CategorieDto.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "L'objet categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'existe dans la BDD avec l'id fournit" )
    })
    CategorieDto findById(@PathVariable("idCat") Integer id);

    @GetMapping(value = route + "categories/byNom/{nomCat}")
    CategorieDto findByNom(@PathVariable("nomCat") String nomCat);


    @GetMapping(value = route + "/categories/all")
    @ApiOperation(value="Renvoie la liste des categories)", notes="Cette methode permet de rechercher et de renvoyer la liste des categories", response = CategorieDto.class)
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategorieDto> findAll();

    @DeleteMapping(value = route + "/categories/delete/{idCat}")
    @ApiOperation(value="Supprimer une categorie)", notes="Cette methode permet de supprimer une categorie par ID", responseContainer = "List<CategorieDto>")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "La categorie supprimé")
    })
    void delete(@PathVariable("idCat") Integer id);

}
