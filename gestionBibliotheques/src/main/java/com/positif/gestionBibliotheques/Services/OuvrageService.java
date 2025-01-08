package com.positif.gestionBibliotheques.Services;

import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface OuvrageService {
    OuvrageDto save(OuvrageDto dto);
    OuvrageDto findById(Integer id);
    OuvrageDto findByNom(String nom);
    List<OuvrageDto> findAll();
    void delete(Integer id);
    public Object download(String fileName) throws IOException;
    public Object upload(Integer id, MultipartFile multipartFile);
}
