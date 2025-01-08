package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeFichier;
import static com.positif.gestionBibliotheques.Utils.Constantes.routeOuvrage;

@Api("Ouvrages")
public interface OuvrageApi {
    @PostMapping(value = routeOuvrage + "/create")
    OuvrageDto save(@RequestBody OuvrageDto dto);
//    , @RequestPart("file") File file
    @GetMapping(value = routeOuvrage + "/all")
    List<OuvrageDto> findAll();
    @GetMapping(value = routeOuvrage + "/{idOuvrage}")
    OuvrageDto findById(@PathVariable("idOuvrage") Integer id);
    @DeleteMapping( value = routeOuvrage + "/delete/{idOuvrage}")
    void delete(@PathVariable("idOuvrage") Integer id);

    @PostMapping(routeFichier + "/upload/{idOuvrage}")
    @CrossOrigin(origins = "*")
    Object upload(@PathVariable("idOuvrage") Integer id, @RequestPart("file") MultipartFile multipartFile);

    @PostMapping(routeFichier + "/download/{fileName}")
    Object download(String fileName) throws IOException;
}
