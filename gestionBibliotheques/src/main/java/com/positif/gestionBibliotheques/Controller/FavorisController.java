package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.FavorisApi;
import com.positif.gestionBibliotheques.Dto.FavorisDto;
import com.positif.gestionBibliotheques.Services.FavorisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FavorisController implements FavorisApi {
    private FavorisService favorisService;
    @Override
    public FavorisDto save(FavorisDto dto) {
        return favorisService.save(dto);
    }

    @Override
    public List<FavorisDto> findAll() {
        return favorisService.findAll();
    }

    @Override
    public List<FavorisDto> findAllByUtilisateurId(Integer id) {
        return favorisService.findAllByIdUtilisateur(id);
    }

    @Override
    public FavorisDto findById(Integer id) {
        return favorisService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        favorisService.delete(id);
    }
}
