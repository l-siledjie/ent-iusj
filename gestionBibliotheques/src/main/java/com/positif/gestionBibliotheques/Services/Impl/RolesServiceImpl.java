package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import com.positif.gestionBibliotheques.Dto.RolesDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Dons;
import com.positif.gestionBibliotheques.Model.Roles;
import com.positif.gestionBibliotheques.Repository.RolesRepository;
import com.positif.gestionBibliotheques.Repository.UtilisateurRepository;
import com.positif.gestionBibliotheques.Services.RolesService;
import com.positif.gestionBibliotheques.Validator.DonsValidator;
import com.positif.gestionBibliotheques.Validator.RolesValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RolesServiceImpl implements RolesService {
    private RolesRepository rolesRepository;
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public RolesServiceImpl(RolesRepository rolesRepository, UtilisateurRepository utilisateurRepository) {
        this.rolesRepository = rolesRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public RolesDto save(RolesDto dto) {
        List<String> errors = RolesValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Role is not valid {}", dto);
            throw new InvalidEntityException("Le role n'est pas valide", ErrorCodes.ROLE_NOT_VALIDE, errors);
        }
        return RolesDto.fromEntity(
                rolesRepository.save(RolesDto.toEntity(dto))
        );
    }

    @Override
    public RolesDto findById(Integer id) {
        if (id == null) {
            log.error("Role ID is null");
            return null;
        }
        return rolesRepository.findById(id)
                .map(RolesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Role avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.ROLE_NOT_FOUND));
    }

    @Override
    public RolesDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<RolesDto> findAll() {
        return rolesRepository.findAll().stream()
                .map(RolesDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
        Optional<Roles> dons = rolesRepository.findById(id);
        if (!dons.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer ce role car elle est deja utilise",
                    ErrorCodes.ROLE_ALREADY_IN_USE);
        }
        rolesRepository.deleteById(id);
    }
}
