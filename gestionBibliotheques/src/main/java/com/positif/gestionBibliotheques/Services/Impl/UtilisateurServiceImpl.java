package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import com.positif.gestionBibliotheques.Dto.RolesDto;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Model.Utilisateur;
import com.positif.gestionBibliotheques.Repository.RolesRepository;
import com.positif.gestionBibliotheques.Repository.UtilisateurRepository;
import com.positif.gestionBibliotheques.Services.UtilisateurService;
import com.positif.gestionBibliotheques.Validator.OuvrageValidator;
import com.positif.gestionBibliotheques.Validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService {
    private UtilisateurRepository utilisateurRepository;
    private RolesRepository rolesRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    public UtilisateurServiceImpl(UtilisateurRepository utilisateurRepository,
                                  RolesRepository rolesRepository,
                                  PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.rolesRepository = rolesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    private boolean userAlreadyExists(String email) {
        Optional<Utilisateur> user = utilisateurRepository.findByEmail(email);
        return user.isPresent();
    }

    @Override
    public UtilisateurDto save(UtilisateurDto dto) {
        List<String> errors = UtilisateurValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Utilisateur is not valid {}", dto);
            throw new InvalidEntityException("L'Utilisateur n'est pas valide", ErrorCodes.UTILISATEUR_NOT_VALIDE, errors);
        }

        if(userAlreadyExists(dto.getEmail())) {
            throw new InvalidEntityException("Un autre Utilisateur avec le meme email existe deja", ErrorCodes.EMPLOYE_ALREADY_EXISTS,
                    Collections.singletonList("Un autre Utilisateur avec le meme email existe deja dans la BDD"));
        }

        dto.setPassWord(passwordEncoder.encode(dto.getPassWord()));

        return UtilisateurDto.fromEntity(
                utilisateurRepository.save(UtilisateurDto.toEntity(dto))
        );
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        if (id == null) {
            log.error("Utiisateur ID is null");
            return null;
        }
        return utilisateurRepository.findById(id)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Utilisateur avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND));
    }

    @Override
    public UtilisateurDto findByNom(String nom) {
        return null;
    }

    @Override
    public UtilisateurDto findByEmail(String email) {
//        if (email == null){
//            log.error("Utilisateur email is null");
//            return null;
//        }
        return utilisateurRepository.findByEmail(email)
                .map(UtilisateurDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun utilisateur avec l'email = " + email +"n'a ete trouver dans la BDD",
                        ErrorCodes.UTILISATEUR_NOT_FOUND
                ));
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
