package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.EmpruntDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Emprunt;
import com.positif.gestionBibliotheques.Repository.EmpruntRepository;
import com.positif.gestionBibliotheques.Repository.OuvrageRepository;
import com.positif.gestionBibliotheques.Repository.UtilisateurRepository;
import com.positif.gestionBibliotheques.Services.EmpruntService;
import com.positif.gestionBibliotheques.Validator.DonsValidator;
import com.positif.gestionBibliotheques.Validator.EmpruntValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmpruntServiceImpl implements EmpruntService {
    private OuvrageRepository ouvrageRepository;
    private UtilisateurRepository utilisateurRepository;
    private EmpruntRepository empruntRepository;
    @Autowired
    public EmpruntServiceImpl(OuvrageRepository ouvrageRepository, UtilisateurRepository utilisateurRepository, EmpruntRepository empruntRepository) {
        this.ouvrageRepository = ouvrageRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.empruntRepository = empruntRepository;
    }


    @Override
    public EmpruntDto save(EmpruntDto dto) {
        List<String> errors = EmpruntValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Emprunt is not valid {}", dto);
            throw new InvalidEntityException("L'emprunt n'est pas valide", ErrorCodes.EMPRUNT_NOT_VALIDE, errors);
        }
        return EmpruntDto.fromEntity(
                empruntRepository.save(EmpruntDto.toEntity(dto))
        );
    }

    @Override
    public EmpruntDto findById(Integer id) {
        if (id == null) {
            log.error("Emprunt ID is null");
            return null;
        }
        return empruntRepository.findById(id)
                .map(EmpruntDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun emprunt avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.EMPRUNT_NOT_FOUND));
    }

    @Override
    public EmpruntDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<EmpruntDto> findAll() {
        return empruntRepository.findAll().stream()
                .map(EmpruntDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Emprunt ID is null");
            return;
        }
        Optional<Emprunt> dons = empruntRepository.findById(id);
        if (!dons.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cet emprunt car il est deja utilise",
                    ErrorCodes.EMPRUNT_ALREADY_IN_USE);
        }
        empruntRepository.deleteById(id);
    }
}
