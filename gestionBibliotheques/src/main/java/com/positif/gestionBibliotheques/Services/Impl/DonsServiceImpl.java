package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.CategorieDto;
import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Categorie;
import com.positif.gestionBibliotheques.Model.Dons;
import com.positif.gestionBibliotheques.Repository.DonsRepository;
import com.positif.gestionBibliotheques.Repository.UtilisateurRepository;
import com.positif.gestionBibliotheques.Services.DonsService;
import com.positif.gestionBibliotheques.Validator.CategorieValidator;
import com.positif.gestionBibliotheques.Validator.DonsValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DonsServiceImpl implements DonsService {
    private DonsRepository donsRepository;
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public DonsServiceImpl(DonsRepository donsRepository, UtilisateurRepository utilisateurRepository) {
        this.donsRepository = donsRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public DonsDto save(DonsDto dto) {
        List<String> errors = DonsValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Don is not valid {}", dto);
            throw new InvalidEntityException("Le don n'est pas valide", ErrorCodes.DON_NOT_VALIDE, errors);
        }
        return DonsDto.fromEntity(
                donsRepository.save(DonsDto.toEntity(dto))
        );
    }

    @Override
    public DonsDto findById(Integer id) {
        if (id == null) {
            log.error("Don ID is null");
            return null;
        }
        return donsRepository.findById(id)
                .map(DonsDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun don avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.DON_NOT_FOUND));
    }

    @Override
    public DonsDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<DonsDto> findAll() {
        return donsRepository.findAll().stream()
                .map(DonsDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
//        Optional<Dons> dons = donsRepository.findById(id);
//        if (!dons.isEmpty()) {
//            throw new InvalidOperationException("Impossible de supprimer ce don car elle est deja utilise",
//                    ErrorCodes.CATEGORIE_ALREADY_IN_USE);
//        }
        donsRepository.deleteById(id);
    }
}
