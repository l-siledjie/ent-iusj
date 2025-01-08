package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.LocalisationDto;

import java.util.ArrayList;
import java.util.List;

public class LocalisationValidator {
    public static List<String> validate(LocalisationDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner le nom de l'emplacement de la localisation");
            errors.add("veuillez renseigner le numero de l'etagere de la localisation");
            errors.add("veuillez renseigner la section de la localisation");
        }else {
            if (dto.getNomEmplacement().equals(null)){
                errors.add("veuillez renseigner le nom de la localisation");
            }
            if (dto.getNumeroEtagere().equals(null)){
                errors.add("veuillez renseigner le numero de l'etagere de la localisation");
            }
            if (dto.getNumeroEtagere().equals(null)){
                errors.add("veuillez renseigner la section de la localisation");
            }
        }

        return errors;
    }
}
