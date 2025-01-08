package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.OuvrageApi;
import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import com.positif.gestionBibliotheques.Services.OuvrageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class OuvrageController implements OuvrageApi {
    private OuvrageService ouvrageService;
    @Override
    public OuvrageDto save(OuvrageDto dto) {
//        , File file
//        dto.setFichier(file);
        return ouvrageService.save(dto);
    }

    @Override
    public List<OuvrageDto> findAll() {
        return ouvrageService.findAll();
    }

    @Override
    public OuvrageDto findById(Integer id) {
        return ouvrageService.findById(id);
    }

    @Override
    public void delete(Integer id) {
        ouvrageService.delete(id);
    }
    @Override
    public Object upload(Integer id, MultipartFile multipartFile) {
        log.info("upload | File Name : {}", multipartFile.getOriginalFilename());
        return ouvrageService.upload(id, multipartFile);
    }
    @Override
    public Object download(String fileName) throws IOException {
        log.info("download | File Name : {}", fileName);
        return ouvrageService.download(fileName);
    }
}
