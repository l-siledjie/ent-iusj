package com.positif.gestionBibliotheques.Services.strategy;

import com.flickr4java.flickr.FlickrException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidOperationException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {
    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;

    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determinContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private void determinContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "ouvrage" :
                strategy = beanFactory.getBean(beanName, SaveOuvragePhoto.class);
                break;
            case "utilisateur" :
                strategy = beanFactory.getBean(beanName, SaveUtilisateurPhoto.class);
                break;
            case "categorie" :
                strategy = beanFactory.getBean(beanName, SaveCategoriePhoto.class);
                break;
            default: throw new InvalidOperationException("Contexte inconnu pour l'enregistrement de la photo", ErrorCodes.UNKNOWN_CONTEXT);
        }
    }


}
