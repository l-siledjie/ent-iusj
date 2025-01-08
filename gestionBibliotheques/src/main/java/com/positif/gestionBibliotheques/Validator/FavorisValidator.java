package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.FavorisDto;

import java.util.ArrayList;
import java.util.List;

public class FavorisValidator {

    public static List<String> validate(FavorisDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner l'ouvrage favoris");
        }

        return errors;
    }

}
