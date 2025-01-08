package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.LocalisationApi;
import com.positif.gestionBibliotheques.Dto.LocalisationDto;
import com.positif.gestionBibliotheques.Services.LocalisationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class LocalisationController implements LocalisationApi {

    private LocalisationService localisationService;
    @Override
    public LocalisationDto save(LocalisationDto dto) {
        return localisationService.save(dto);
    }

    @Override
    public List<LocalisationDto> findAll() {
        return localisationService.findAll();
    }

    @Override
    public LocalisationDto findById(Integer id) {
        return localisationService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        localisationService.delete(id);
    }
}
