package com.positif.gestionBibliotheques.Validator;

import com.positif.gestionBibliotheques.Dto.CategorieDto;
import com.positif.gestionBibliotheques.Dto.DonsDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DonsValidator {
    public static List<String> validate(DonsDto dto){
        List<String> errors = new ArrayList<>();

        if ((dto.equals(null))){
            errors.add("veuillez renseigner le montant du don");
            errors.add("veuillez renseigner la date du don");
        }else {
            if (dto.getMontant().equals(null)){
                errors.add("veuillez renseigner le montant du don");
            }
            if (dto.getDate().equals(null)){
                errors.add("veuillez renseigner la date du don");
            }
        }

        return errors;
    }
}
