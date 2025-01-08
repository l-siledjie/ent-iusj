package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.UtilisateurApi;
import com.positif.gestionBibliotheques.Dto.RolesDto;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Services.RolesService;
import com.positif.gestionBibliotheques.Services.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UtilisateurController implements UtilisateurApi {
    private UtilisateurService utilisateurService;
    private RolesService rolesService;
    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
//        dto.setRoles(RolesDto.toEntity(rolesService.findById(1)));
        return utilisateurService.save(dto);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public UtilisateurDto findById(@PathVariable("id") Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public UtilisateurDto findByEmail(@PathVariable("emailUser") String email) {
        return utilisateurService.findByEmail(email);
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }
}
