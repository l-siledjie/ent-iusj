package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.LigneVenteApi;
import com.positif.gestionBibliotheques.Dto.LigneVenteDto;
import com.positif.gestionBibliotheques.Services.LigneVenteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
@AllArgsConstructor
public class LigneVenteController implements LigneVenteApi {
    private LigneVenteService ligneVenteService;
    @Override
    public LigneVenteDto save(LigneVenteDto dto) {
        return ligneVenteService.save(dto);
    }

    @Override
    public List<LigneVenteDto> findAll() {
        return ligneVenteService.findAll();
    }

    @Override
    public LigneVenteDto findById(Integer id) {
        return ligneVenteService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        ligneVenteService.delete(id);
    }
}
