package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.VenteApi;
import com.positif.gestionBibliotheques.Dto.VenteDto;
import com.positif.gestionBibliotheques.Services.VenteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class VenteController implements VenteApi {
    private VenteService venteService;
    @Override
    public VenteDto save(VenteDto dto) {
        return venteService.save(dto);
    }

    @Override
    public List<VenteDto> findAll() {
        return venteService.findAll();
    }

    @Override
    public VenteDto findById(Integer id) {
        return venteService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        venteService.delete(id);
    }
}
