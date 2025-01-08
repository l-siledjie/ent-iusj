package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {
    public static List<String> validate(UtilisateurDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner l'email de l'utilisateur");
            errors.add("veuillez renseigner le nom de l'utilisateur");
            errors.add("veuillez renseigner le role de l'utilisateur");
        }else {
            if (!StringUtils.hasLength(dto.getEmail())){
                errors.add("veuillez renseigner l'email de l'utilisateur");
            }
            if (!StringUtils.hasLength(dto.getNom())){
                errors.add("veuillez renseigner le nom de l'utilisateur");
            }
            if (!StringUtils.hasLength(dto.getRoles().getNom())){
                errors.add("veuillez renseigner le role de l'utilisateur");
            }
        }

        return errors;
    }
}
