package com.positif.gestionBibliotheques.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Services.FlickrService;
import com.positif.gestionBibliotheques.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.InputStream;

public class SaveUtilisateurPhoto implements Strategy<UtilisateurDto>{
    private FlickrService flickrService;
    private UtilisateurService utilisateurService;

    @Autowired
    public SaveUtilisateurPhoto(FlickrService flickrService, UtilisateurService utilisateurService) {
        this.flickrService = flickrService;
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        UtilisateurDto medicament = utilisateurService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'utilisateur", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        medicament.setPhoto(urlPhoto);
        return utilisateurService.save(medicament);
    }
}
