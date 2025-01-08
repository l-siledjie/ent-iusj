package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.CategorieApi;
import com.positif.gestionBibliotheques.Dto.CategorieDto;
import com.positif.gestionBibliotheques.Services.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategorieController implements CategorieApi {

    private CategorieService categorieService;

    @Override
    public CategorieDto save(@RequestBody CategorieDto dto){
        return categorieService.save(dto);
    }


    @Override
    public List<CategorieDto> findAll(){
        return categorieService.findAll();
    }

//    @Override
//    public CategorieDto addMedicamentsToCategorie(Integer id, List<String> nomMedicaments) {
//        categorieService
//    }

    @Override
    public CategorieDto findById(@PathVariable("idCat") Integer id){
        return categorieService.findById(id);

    }

    @Override
    public CategorieDto findByNom(@PathVariable("nomCat") String nom){
        return categorieService.findByNom(nom);

    }

    public void delete(@PathVariable("idCat") Integer id){
        categorieService.delete(id);
    }
}
