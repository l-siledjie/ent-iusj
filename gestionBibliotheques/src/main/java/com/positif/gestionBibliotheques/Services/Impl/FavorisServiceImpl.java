package com.positif.gestionBibliotheques.Services.Impl;

import com.positif.gestionBibliotheques.Dto.FavorisDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Model.Favoris;
import com.positif.gestionBibliotheques.Model.Ouvrage;
import com.positif.gestionBibliotheques.Model.Utilisateur;
import com.positif.gestionBibliotheques.Repository.FavorisRepository;
import com.positif.gestionBibliotheques.Repository.OuvrageRepository;
import com.positif.gestionBibliotheques.Repository.UtilisateurRepository;
import com.positif.gestionBibliotheques.Services.FavorisService;
import com.positif.gestionBibliotheques.Validator.FavorisValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FavorisServiceImpl implements FavorisService {
    private FavorisRepository favorisRepository;
    private UtilisateurRepository utilisateurRepository;
    private OuvrageRepository ouvrageRepository;
    @Autowired
    public FavorisServiceImpl(FavorisRepository favorisRepository, UtilisateurRepository utilisateurRepository, OuvrageRepository ouvrageRepository) {
        this.favorisRepository = favorisRepository;
        this.utilisateurRepository = utilisateurRepository;
        this.ouvrageRepository = ouvrageRepository;
    }

    private boolean ouvrageExists(Integer id) {
        Optional<Ouvrage> ouvrage = ouvrageRepository.findById(id);
        return ouvrage.isPresent();
    }

    private boolean userExists(Integer id) {
        Optional<Utilisateur> user = utilisateurRepository.findById(id);
        return user.isPresent();
    }

    @Override
    public FavorisDto save(FavorisDto dto) {
//        if ()
        List<String> errors = FavorisValidator.validate(dto);
        if(!this.userExists(dto.getIdUtilisateur())){
            log.error("user not found ", dto.getIdUtilisateur());
            throw new InvalidEntityException("Aucun utilisateur avec l'id" + dto.getIdUtilisateur() + " n'a ete trouve dans la bd", ErrorCodes.FAVORIS_NOT_VALIDE, errors);
        }
        if(!this.ouvrageExists(dto.getOuvrageDto().getId())){
            log.error("ouvrage not found ", dto.getOuvrageDto().getId());
            throw new InvalidEntityException("Aucun ouvrage avec l'id" + dto.getOuvrageDto().getId() + " n'a ete trouve dans la bd", ErrorCodes.FAVORIS_NOT_VALIDE, errors);
        }
        if (!errors.isEmpty()) {
            log.error("Favoris is not valid {}", dto);
            throw new InvalidEntityException("Le Favoris n'est pas valide", ErrorCodes.FAVORIS_NOT_VALIDE, errors);
        }
        return FavorisDto.fromEntity(
                favorisRepository.save(FavorisDto.toEntity(dto))
        );
    }

    @Override
    public FavorisDto findById(Integer id) {
        if (id == null) {
            log.error("Favoris ID is null");
            return null;
        }
        return favorisRepository.findById(id)
                .map(FavorisDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Favoris avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.FAVORIS_NOT_FOUND));
    }


    @Override
    public List<FavorisDto> findAll() {
        return favorisRepository.findAll().stream()
                .map(FavorisDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<FavorisDto> findAllByIdUtilisateur(Integer id) {
        return favorisRepository.findAllByIdUtilisateur(id).stream()
                .map(FavorisDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
        Optional<Favoris> dons = favorisRepository.findById(id);
        if (!dons.isEmpty()) {
            throw new InvalidOperationException("Impossible de supprimer ce Favoris car elle est deja utilise",
                    ErrorCodes.FAVORIS_ALREADY_IN_USE);
        }
        favorisRepository.deleteById(id);
    }
}


