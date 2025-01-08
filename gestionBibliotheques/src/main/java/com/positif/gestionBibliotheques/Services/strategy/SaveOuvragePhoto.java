package com.positif.gestionBibliotheques.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Services.FlickrService;
import com.positif.gestionBibliotheques.Services.OuvrageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("ouvrageStrategy")
@Slf4j
public class SaveOuvragePhoto implements Strategy<OuvrageDto>{
    private FlickrService flickrService;
    private OuvrageService ouvrageService;

    @Autowired
    public SaveOuvragePhoto(FlickrService flickrService, OuvrageService ouvrageService) {
        this.flickrService = flickrService;
        this.ouvrageService = ouvrageService;
    }

    @Override
    public OuvrageDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        OuvrageDto ouvrage = ouvrageService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de l'ouvrage", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        ouvrage.setPhoto(urlPhoto);
        return ouvrageService.save(ouvrage);
    }
}
