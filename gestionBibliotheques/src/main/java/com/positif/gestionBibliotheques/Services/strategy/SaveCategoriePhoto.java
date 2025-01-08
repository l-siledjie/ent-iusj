package com.positif.gestionBibliotheques.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.positif.gestionBibliotheques.Dto.CategorieDto;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import com.positif.gestionBibliotheques.Services.FlickrService;
import com.positif.gestionBibliotheques.Services.CategorieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;

@Service("categorieStrategy")
@Slf4j
public class SaveCategoriePhoto implements Strategy<CategorieDto>{
    private FlickrService flickrService;
    private CategorieService categorieService;

    @Autowired
    public SaveCategoriePhoto(FlickrService flickrService, CategorieService categorieService) {
        this.flickrService = flickrService;
        this.categorieService = categorieService;
    }

    @Override
    public CategorieDto savePhoto(Integer id, InputStream photo, String titre) throws FlickrException {
        CategorieDto medicament = categorieService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, titre);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de la photo de la categorie", ErrorCodes.UPDATE_PHOTO_EXCEPTION);
        }
        medicament.setPhoto(urlPhoto);
        return categorieService.save(medicament);
    }
}
