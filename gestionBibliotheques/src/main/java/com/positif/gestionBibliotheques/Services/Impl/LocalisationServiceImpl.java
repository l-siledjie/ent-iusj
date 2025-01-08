package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.LocalisationDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Localisation;
import com.positif.gestionBibliotheques.Repository.LocalisationRepository;
import com.positif.gestionBibliotheques.Services.LocalisationService;
import com.positif.gestionBibliotheques.Validator.LocalisationValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LocalisationServiceImpl implements LocalisationService {

    private LocalisationRepository localisationRepository;

    @Autowired
    public LocalisationServiceImpl(LocalisationRepository localisationRepository) {
        this.localisationRepository = localisationRepository;
    }

    @Override
    public LocalisationDto save(LocalisationDto dto) {
        List<String> errors = LocalisationValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Localisation is not valid {}", dto);
            throw new InvalidEntityException("La Localisation n'est pas valide", ErrorCodes.LOCALISATION_NOT_FOUND, errors);
        }
        return LocalisationDto.fromEntity(
                localisationRepository.save(LocalisationDto.toEntity(dto))
        );
    }

    @Override
    public LocalisationDto findById(Integer id) {
        if (id == null) {
            log.error("Localisation ID is null");
            return null;
        }
        return localisationRepository.findById(id)
                .map(LocalisationDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune Localisation avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.LOCALISATION_NOT_FOUND));
    }

    @Override
    public List<LocalisationDto> findAll() {
        return localisationRepository.findAll().stream()
                .map(LocalisationDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Localisation ID is null");
            return;
        }
        Optional<Localisation> dons = localisationRepository.findById(id);
        if (!dons.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cette localisation car elle est deja utilise",
                    ErrorCodes.LOCALISATION_ALREADY_IN_USE);
        }
        localisationRepository.deleteById(id);
    }
}
