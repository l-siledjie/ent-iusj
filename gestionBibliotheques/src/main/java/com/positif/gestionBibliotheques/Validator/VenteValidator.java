package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.VenteDto;

import java.util.ArrayList;
import java.util.List;

public class VenteValidator {
    public static List<String> validate(VenteDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner le montant du don");
//            errors.add("veuillez renseigner la date du don");
        }else {
            if (dto.getDate().equals(null)){
                errors.add("veuillez renseigner la date de la vente");
            }
//            if (dto.getDate().equals(null)){
//                errors.add("veuillez renseigner la date du don");
//            }
        }

        return errors;
    }
}
