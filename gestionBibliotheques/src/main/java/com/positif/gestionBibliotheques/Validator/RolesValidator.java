package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.DonsDto;
import com.positif.gestionBibliotheques.Dto.RolesDto;

import java.util.ArrayList;
import java.util.List;

public class RolesValidator {
    public static List<String> validate(RolesDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner le nom du role");
//            errors.add("veuillez renseigner le nom du role");
        }else {
            if (dto.getNom().equals(null)){
                errors.add("veuillez renseigner le nom du role");
            }
//            if (dto.getDate().equals(null)){
//                errors.add("veuillez renseigner la date du don");
//            }
        }

        return errors;
    }
}
