package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.EmpruntApi;
import com.positif.gestionBibliotheques.Dto.EmpruntDto;
import com.positif.gestionBibliotheques.Services.EmpruntService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class EmpruntController implements EmpruntApi {
    private EmpruntService empruntService;
    @Override
    public EmpruntDto save(EmpruntDto dto) {
        return empruntService.save(dto);
    }

    @Override
    public List<EmpruntDto> findAll() {
        return null;
    }

    @Override
    public EmpruntDto findById(Integer id) {
        return empruntService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        empruntService.delete(id);
    }
}
