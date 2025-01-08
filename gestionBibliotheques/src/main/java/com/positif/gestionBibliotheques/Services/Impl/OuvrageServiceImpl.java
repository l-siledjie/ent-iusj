package com.positif.gestionBibliotheques.Services.Impl;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import com.positif.gestionBibliotheques.Dto.OuvrageDto;
import com.positif.gestionBibliotheques.Exceptions.EntityNotFoundException;
import com.positif.gestionBibliotheques.Exceptions.ErrorCodes;
import com.positif.gestionBibliotheques.Exceptions.InvalidEntityException;
import com.positif.gestionBibliotheques.Model.Ouvrage;
import com.positif.gestionBibliotheques.Repository.CategorieRepository;
import com.positif.gestionBibliotheques.Repository.EmpruntRepository;
import com.positif.gestionBibliotheques.Repository.OuvrageRepository;
import com.positif.gestionBibliotheques.Services.OuvrageService;
import com.positif.gestionBibliotheques.Validator.OuvrageValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OuvrageServiceImpl implements OuvrageService {
    private OuvrageRepository ouvrageRepository;
    private CategorieRepository categorieRepository;
    private EmpruntRepository empruntRepository;

    @Autowired
    public OuvrageServiceImpl(OuvrageRepository ouvrageRepository, CategorieRepository categorieRepository, EmpruntRepository empruntRepository) {
        this.ouvrageRepository = ouvrageRepository;
        this.categorieRepository = categorieRepository;
        this.empruntRepository = empruntRepository;
    }

    @Override
    public OuvrageDto save(OuvrageDto dto) {
        List<String> errors = OuvrageValidator.validate(dto);
        if (!errors.isEmpty()) {
            log.error("Ouvrage is not valid {}", dto);
            throw new InvalidEntityException("L'ouvrage n'est pas valide", ErrorCodes.OUVRAGE_NOT_VALIDE, errors);
        }
        return OuvrageDto.fromEntity(
                ouvrageRepository.save(OuvrageDto.toEntity(dto))
        );
    }

    @Override
    public OuvrageDto findById(Integer id) {
        if (id == null) {
            log.error("Ouvrage ID is null");
            return null;
        }
        return ouvrageRepository.findById(id)
                .map(OuvrageDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucun Ouvrage avec l'ID = " + id + " n'a ete trouve dans la BDD",
                        ErrorCodes.OUVRAGE_NOT_FOUND));
    }

    @Override
    public OuvrageDto findByNom(String nom) {
        return null;
    }

    @Override
    public List<OuvrageDto> findAll() {
        return ouvrageRepository.findAll().stream()
                .map(OuvrageDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id.equals(null)){
            log.error("Don ID is null");
            return;
        }
        Optional<Ouvrage> ouvrages = ouvrageRepository.findById(id);
//        if (!ouvrages.isEmpty()) {
//            throw new InvalidOperationException("Impossible de supprimer cet ouvrage car il est deja utilise",
//                    ErrorCodes.OUVRAGE_ALREADY_IN_USE);
//        }
        ouvrageRepository.deleteById(id);
    }

    private String uploadFile(File file, String fileName) throws IOException {
        String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/busuisj.appspot.com/o/%s?alt=media";
        String folderName = "Ouvrages/";
        fileName = folderName + fileName;
        BlobId blobId = BlobId.of("busuisj.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase-private-key.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(multipartFile.getBytes());
            fos.close();
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private Map<String, Object> sendResponse(String message, String url) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("url", url);
        return response;
    }

    private Map<String, Object> sendResponse(String message, String url, File file) {
        Map<String, Object> response = new HashMap<>();
        response.put("message", message);
        response.put("url", url);
        response.put("file", file);
        return response;
    }


    @Override
    public Object upload(Integer id, MultipartFile multipartFile) {
        OuvrageDto ouvrage = this.findById(id);
        try {
            String fileName = multipartFile.getOriginalFilename();                        // to get original file name
//            log.info("nom 1: "+fileName);
//            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));  // to generated random string values for file name.
//            log.info("nom 2:  "+fileName);
            File file = this.convertToFile(multipartFile, fileName);                      // to convert multipartFile to File
            String TEMP_URL = this.uploadFile(file, fileName);                                   // to get uploaded file link
            file.delete();                                                                // to delete the copy of uploaded file stored in the project folder
//            return sendResponse("Successfully Uploaded !", TEMP_URL);                     // Your customized response
            ouvrage.setFichier(fileName);
            return this.save(ouvrage);
        } catch (Exception e) {
            e.printStackTrace();
            return sendResponse("500", "Unsuccessfully Uploaded!");
        }

    }

//    @Override
//    public Object download(String fileName) throws IOException {
//        String destFileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));     // to set random strinh for destination file name
//        String destFilePath = "Ouvrages\\"+ destFileName;
//        String folderName = "Ouvrages/";
//        fileName = folderName + fileName;
//        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
//        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase-private-key.json"));
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//        Blob blob = storage.get(BlobId.of("busuisj.appspot.com", fileName));
//        blob.downloadTo(Paths.get(destFilePath));
//        return sendResponse("200", "Successfully Downloaded!");
//    }

    @Override
    public Object download(String fileName) throws IOException {
        String destFileName = fileName;    // to set random strinh for destination file name
        String destFilePath = "src/main/resources/Ouvrages/"+ destFileName;
        String folderName = "Ouvrages/";
        fileName = folderName + fileName;
        ////////////////////////////////   Download  ////////////////////////////////////////////////////////////////////////
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase-private-key.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        Blob blob = storage.get(BlobId.of("busuisj.appspot.com", fileName));
        blob.downloadTo(Paths.get(destFilePath));
        File downloadedFile = new File(destFilePath);
        return sendResponse("200", "Successfully Downloaded!", downloadedFile);
    }

//    @Override
//    public File download(String fileName) throws IOException {
//        String destFileName = fileName; // pour définir un nom de fichier de destination aléatoire
//        String destFilePath = "src/main/resources/Ouvrages/"+ destFileName;
//        String folderName = "Ouvrages/";
//        fileName = folderName + fileName;
//
//        // Téléchargement depuis Google Cloud Storage
//        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream("src/main/resources/firebase-private-key.json"));
//        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
//        Blob blob = storage.get(BlobId.of("busuisj.appspot.com", fileName));
//        blob.downloadTo(Paths.get(destFilePath));
//
//        // Représentation du fichier téléchargé sous forme d'objet File
//        File downloadedFile = new File(destFilePath);
//
//        return downloadedFile; // Renvoyer l'objet File représentant le fichier téléchargé
//    }
}
