package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.DonsApi;
import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Services.DonsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class DonsController implements DonsApi {

    private DonsService donsService;
    @Override
    public DonsDto save(DonsDto dto) {
        return donsService.save(dto);
    }

    @Override
    public List<DonsDto> findAll() {
        return donsService.findAll();
    }

    @Override
    public DonsDto findById(Integer id) {
        return donsService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        donsService.delete(id);
    }
}
