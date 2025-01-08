package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.LigneVenteDto;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.LigneVente;
import com.positif.gestionBibliotheques.Repository.LigneVenteRepository;
import com.positif.gestionBibliotheques.Repository.OuvrageRepository;
import com.positif.gestionBibliotheques.Repository.VenteRepository;
import com.positif.gestionBibliotheques.Services.LigneVenteService;
import com.positif.gestionBibliotheques.Validator.LigneVenteValidator;
import com.positif.gestionBibliotheques.Validator.UtilisateurValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LigneVenteServiceImpl implements LigneVenteService {

    private LigneVenteRepository ligneVenteRepository;
    private VenteRepository venteRepository;
    private OuvrageRepository ouvrageRepository;

    @Autowired
    public LigneVenteServiceImpl(LigneVenteRepository ligneVenteRepository, VenteRepository venteRepository, OuvrageRepository ouvrageRepository) {
        this.ligneVenteRepository = ligneVenteRepository;
        this.venteRepository = venteRepository;
        this.ouvrageRepository = ouvrageRepository;
    }

    @Override
    public LigneVenteDto save(LigneVenteDto dto) {
        List<String> errors = LigneVenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Ligne de vente is not valid {}", dto);
            throw new InvalidEntityException("La ligne de vente n'est pas valide", ErrorCodes.LIGNE_VENTE_NOT_VALIDE, errors);
        }
        return LigneVenteDto.fromEntity(
                ligneVenteRepository.save(LigneVenteDto.toEntity(dto))
        );
    }

    @Override
    public LigneVenteDto findById(Integer id) {
        if (id == null) {
            log.error("LigneVente ID is null");
            return null;
        }
        return ligneVenteRepository.findById(id)
                .map(LigneVenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune LigneVente avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.LIGNE_VENTE_NOT_FOUND));
    }

    @Override
    public LigneVenteDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<LigneVenteDto> findAll() {
        return ligneVenteRepository.findAll().stream()
                .map(LigneVenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
        Optional<LigneVente> dons = ligneVenteRepository.findById(id);
        if (!dons.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cette ligne de vente car elle est deja utilise",
                    ErrorCodes.LIGNE_VENTE_ALREADY_IN_USE);
        }
        ligneVenteRepository.deleteById(id);
    }
}
