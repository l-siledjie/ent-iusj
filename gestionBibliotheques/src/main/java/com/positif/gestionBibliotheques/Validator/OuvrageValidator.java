package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class OuvrageValidator {
    public static List<String> validate(OuvrageDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner le le nom de l'auteur de l'ouvrage");
            errors.add("veuillez renseigner le nom de l'ouvrage");
            errors.add("veuillez renseigner la nature de l'ouvrage");
            errors.add("veuillez renseigner le montant de l'ouvrage");
            errors.add("veuillez renseigner le le type de l'ouvrage");
        }else {
            if (!StringUtils.hasLength(dto.getAuteur())){
                errors.add("veuillez renseigner le le nom de l'auteur de l'ouvrage");
            }
            if (!StringUtils.hasLength(dto.getNom())){
                errors.add("veuillez renseigner le nom de l'ouvrage");
            }
            if (!StringUtils.hasLength(dto.getNature())){
                errors.add("veuillez renseigner la nature de l'ouvrage");
            }
            if (dto.getPrixUnitaire().equals(null)){
                errors.add("veuillez renseigner le montant de l'ouvrage");
            }
            if (!dto.getNature().equals("physique") && dto.getFichier().equals(null)){
                errors.add("veuillez renseigner un fichier pour l'ouvrage");
            }
            if (!StringUtils.hasLength(dto.getType())){
                errors.add("veuillez renseigner le le type de l'ouvrage");
            }
        }

        return errors;
    }
}
