package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.CategorieDto;
import com.positif.gestionBibliotheques.Dto.EmpruntDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EmpruntValidator {
    public static List<String> validate(EmpruntDto empruntDto){
        List<String> errors = new ArrayList<>();

        if (empruntDto.equals(null)){
            errors.add("veuillez renseigner le motif de l'emprunt");
            errors.add("veuillez renseigner la date de restitution de l'emprunt");
        }else {
            if (!StringUtils.hasLength(empruntDto.getMotif())){
                errors.add("veuillez renseigner le motif de l'emprunt");
            }
//            if(if(empruntDto.getDateRestitution().equals(null)){
//                errors.add("veuillez renseigner la date de restitution de l'emprunt");
//            }
            if(empruntDto.getDateRestitution().equals(null)){
                errors.add("veuillez renseigner la date de restitution de l'emprunt");
            }
        }

        return errors;
    }
}
