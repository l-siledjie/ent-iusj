package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.CategorieDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Repository.CategorieRepository;
import com.positif.gestionBibliotheques.Repository.OuvrageRepository;
import com.positif.gestionBibliotheques.Services.CategorieService;
import com.positif.gestionBibliotheques.Validator.CategorieValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategorieServiceImplementation implements CategorieService {

    private CategorieRepository categorieRepository;
    private OuvrageRepository ouvrageRepository;

    @Autowired
    public CategorieServiceImplementation(CategorieRepository categorieRepository,
                                          OuvrageRepository ouvrageRepository){
        this.categorieRepository = categorieRepository;
        this.ouvrageRepository = ouvrageRepository;
    }

    private boolean categorieAlreadyExists(String nom) {
        Optional<Categorie> categorie = categorieRepository.findByNom(nom);
        return categorie.isPresent();
    }

    @Override
    public CategorieDto save(CategorieDto dto) {
        List<String> errors = CategorieValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Categorie is not valid {}", dto);
            throw new InvalidEntityException("La Categorie n'est pas valide", ErrorCodes.CATEGORIE_NOT_VALIDE, errors);
        }
        if(categorieAlreadyExists(dto.getNom())) {
            throw new InvalidEntityException("Une autre Categorie avec le meme nom existe deja", ErrorCodes.CATEGORIE_ALREADY_EXIST,
                    Collections.singletonList("Une autre Categorie avec le meme nom existe deja dans la BDD"));
        }
        return CategorieDto.fromEntity(
                categorieRepository.save(CategorieDto.toEntity(dto))
        );
    }

    @Override
    public CategorieDto findById(Integer id) {
        if (id == null) {
            log.error("Categorie ID is null");
            return null;
        }
        return categorieRepository.findById(id)
                .map(CategorieDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune categorie avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND));
    }

    @Override
    public CategorieDto findByNom(String nom) {
        return categorieRepository.findByNom(nom)
                .map(CategorieDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune categorie avec le nom = " + nom + " n'a ete trouve dans la BDD",
                        ErrorCodes.CATEGORIE_NOT_FOUND));
    }

    @Override
    public List<CategorieDto> findAll() {
        return categorieRepository.findAll().stream()
                .map(CategorieDto::fromEntity)
                .collect(Collectors.toList());
    }


    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Categorie ID is null");
            return;
        }
        Optional<Categorie> categories = categorieRepository.findById(id);
        if (!categories.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cette categorie car elle est deja utilise",
                    ErrorCodes.CATEGORIE_ALREADY_IN_USE);
        }
        categorieRepository.deleteById(id);
    }
}
