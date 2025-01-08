package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Dto.VenteDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Vente;
import com.positif.gestionBibliotheques.Repository.LigneVenteRepository;
import com.positif.gestionBibliotheques.Repository.UtilisateurRepository;
import com.positif.gestionBibliotheques.Repository.VenteRepository;
import com.positif.gestionBibliotheques.Services.VenteService;
import com.positif.gestionBibliotheques.Validator.DonsValidator;
import com.positif.gestionBibliotheques.Validator.VenteValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VenteService {
    private VenteRepository venteRepository;
    private LigneVenteRepository ligneVenteRepository;
    private UtilisateurRepository utilisateurRepository;
    @Autowired
    public VenteServiceImpl(VenteRepository venteRepository, LigneVenteRepository ligneVenteRepository, UtilisateurRepository utilisateurRepository) {
        this.venteRepository = venteRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public VenteDto save(VenteDto dto) {
        List<String> errors = VenteValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Vente is not valid {}", dto);
            throw new InvalidEntityException("La vente n'est pas valide", ErrorCodes.VENTE_NOT_VALIDE, errors);
        }
        return VenteDto.fromEntity(
                venteRepository.save(VenteDto.toEntity(dto))
        );
    }

    @Override
    public VenteDto findById(Integer id) {
        if (id == null) {
            log.error("Vente ID is null");
            return null;
        }
        return venteRepository.findById(id)
                .map(VenteDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune vente avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.VENTE_NOT_FOUND));
    }

    @Override
    public VenteDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<VenteDto> findAll() {
        return venteRepository.findAll().stream()
                .map(VenteDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
        Optional<Vente> dons = venteRepository.findById(id);
        if (!dons.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer cette vente car elle est deja utilise",
                        ErrorCodes.VENTE_ALREADY_IN_USE);
        }
        venteRepository.deleteById(id);
    }
}
