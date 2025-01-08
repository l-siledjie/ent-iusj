package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.LigneVenteDto;

import java.util.ArrayList;
import java.util.List;

public class LigneVenteValidator {
    public static List<String> validate(LigneVenteDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner la quantite d'ouvrages");
            errors.add("veuillez renseigner le prix total des ouvrages");
        }else {
            if (dto.getQuantite().equals(null)){
                errors.add("veuillez renseigner la quantite d'ouvrages");
            }
            if (dto.getPrixTotal().equals(null)){
                errors.add("veuillez renseigner le prix total des ouvrages");
            }
        }

        return errors;
    }
}
