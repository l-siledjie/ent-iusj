package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import com.isj.gestiondenote.ClientWeb.Model.dto.CategorieDto;
import com.isj.gestiondenote.ClientWeb.Model.dto.EmpruntDto;
import com.isj.gestiondenote.ClientWeb.Model.dto.OuvrageDto;
import com.isj.gestiondenote.ClientWeb.Model.entities.Enseignant;
import com.isj.gestiondenote.ClientWeb.Model.entities.Utilisateur;
import com.isj.gestiondenote.ClientWeb.utils.test.Modal;
import com.isj.gestiondenote.ClientWeb.utils.test.ModalWithHttpHeader;
import com.isj.gestiondenote.ClientWeb.utils.test.RequestInterceptor;
import com.isj.gestiondenote.ClientWeb.utils.test.URL;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Controller
public class BiblioController {

    @GetMapping("/gestionbibliotheque")
    public String pageProfileBiblio(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/gestBiblio";
    }

    @GetMapping("/gestemprunts")
    public String pageEmpruntBiblio(Model model, HttpSession session){
//        ModalWithHttpHeader.model(model, session);
//        String accessToken = (String) session.getAttribute("accessToken");
//        model.addAttribute("accessToken", accessToken);

        RestTemplate restTemplate = new RestTemplate();

        Object[] emprunts = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/emprunt/all", Object[].class);
        model.addAttribute("emprunts", emprunts);
        return "layout/gestionbiblio/empruntBiblio";
    }

    @GetMapping("/newEmprunt")
    public String pageNewEmpruntBiblio(Model model, HttpSession session){
//        ModalWithHttpHeader.model(model, session);
        EmpruntDto emprunt = new EmpruntDto();

        RestTemplate restTemplate = new RestTemplate();

        Object[] ouvrages = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/ouvrages/all", Object[].class);
        model.addAttribute("ouvrages", ouvrages);

        model.addAttribute("emprunt", emprunt);
        return "layout/gestionbiblio/createEmprunt";
    }

    @GetMapping("/mesfavoris")
    public String pageFavorisBiblio(Model model, HttpSession session){
//        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/favoris";
    }

    @GetMapping("/details")
    public String pageDetailsBiblio(Model model, HttpSession session){
//        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/detailOuvrage";
    }

    @GetMapping("/createcategorie")
    public String pageNewCategorieBiblio(Model model, HttpSession session){
//        ModalWithHttpHeader.model(model, session);
        CategorieDto categorie1 = new CategorieDto();
        model.addAttribute("categorie1", categorie1);
        return "layout/gestionbiblio/createCategorie";
    }

    @GetMapping("/createouvrage")
    public String pageNewOuvrageBiblio(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);

        // Récupérer les catégories depuis l'API
        RestTemplate restTemplate = new RestTemplate();
        Object[] categories = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/categories/all", Object[].class);
        // Récupérer les emplacements depuis l'API Localisation
        Object[] emplacements = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/localisation/all", Object[].class);

        OuvrageDto ouvrage1 = new OuvrageDto();
        model.addAttribute("ouvrage1", ouvrage1);
        model.addAttribute("categories", categories); // Ajouter les catégories au modèle
        model.addAttribute("emplacements", emplacements); // Ajouter les emplacements au modèle

        return "layout/gestionbiblio/createOuvrage";
    }

    @GetMapping("/gestionOuvragebiblio")
    public String pageOuvragBiblio(Model model, HttpSession session){
        ModalWithHttpHeader.model(model, session);
        return "layout/gestionbiblio/Ouvrages";
    }

    @GetMapping("/listCategories")
    public ModelAndView listeCategories(Model model, HttpSession session) {
//        ModalWithHttpHeader.model(model, session);
//        Modal.model(model);
//        String accessToken = (String) session.getAttribute("accessToken");
//        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        RestTemplate restTemplate = new RestTemplate();

        Object[] categories = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/categories/all", Object[].class);
        model.addAttribute("categories", categories);

        System.out.println(model);
        return new ModelAndView("layout/gestionbiblio/ListCategories");
    }

    @GetMapping("/listEmplacements")
    public ModelAndView listeEmplacement(Model model, HttpSession session) {
//        ModalWithHttpHeader.model(model, session);
//        Modal.model(model);
//        String accessToken = (String) session.getAttribute("accessToken");
//        model.addAttribute("accessToken", accessToken);
        System.out.println(model);
        RestTemplate restTemplate = new RestTemplate();

        Object[] emplacements = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/localisation/all", Object[].class);
        model.addAttribute("emplacements", emplacements);

        System.out.println(model);
        return new ModelAndView("layout/gestionbiblio/ListEmplacement");
    }

    @GetMapping("/listOuvrages")
    public ModelAndView listeOuvrages(Model model, HttpSession session) {
//        ModalWithHttpHeader.model(model, session);
//        Modal.model(model);
//        String accessToken = (String) session.getAttribute("accessToken");
//        model.addAttribute("accessToken", accessToken);
//        System.out.println(model);
        RestTemplate restTemplate = new RestTemplate();

        Object[] ouvrages = restTemplate.getForObject(URL.BASE_URL_BIBLIO + "/ouvrages/all", Object[].class);
        model.addAttribute("ouvrages", ouvrages);

        System.out.println(model);
        return new ModelAndView("layout/gestionbiblio/ListOuvrages");

    }

    @PostMapping("/saveEmprunt")
    public String creerEmprunt(@ModelAttribute EmpruntDto emprunt, Model model) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<EmpruntDto> httpEntity = new HttpEntity<>(emprunt, headers);
        System.out.println(emprunt);

        CompletableFuture.runAsync(() -> {
            restTemplate.postForObject(
                    URI.create(URL.BASE_URL_BIBLIO + "/emprunt/create"),
                    httpEntity,
                    Object[].class
            );
        });

        return "redirect:/gestemprunts";
    }

    @PostMapping("/newCategorie")
    public String creerCategorie(@ModelAttribute CategorieDto categorie, Model model) throws URISyntaxException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<CategorieDto> httpEntity = new HttpEntity<>(categorie, headers);
        System.out.println(categorie);

        CompletableFuture.runAsync(() -> {
            restTemplate.postForObject(
                    URI.create(URL.BASE_URL_BIBLIO + "/categories/create"),
                    httpEntity,
                    Object[].class
            );
        });

        return "redirect:/listCategories";
    }

    @PostMapping("/newOuvrage")
    public String creerOuvrage(@ModelAttribute OuvrageDto ouvrage, Model model) {
        System.out.println("debutttttttt");
        System.out.println(ouvrage.getCategorieDto().getId());
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            RestTemplate restTemplate = new RestTemplate();

            HttpEntity<OuvrageDto> httpEntity = new HttpEntity<>(ouvrage, headers);

            CompletableFuture.runAsync(() -> {
                restTemplate.postForObject(
                        URI.create(URL.BASE_URL_BIBLIO + "/ouvrages/create"),
                        httpEntity,
                        Object[].class
                );
            });
            return "redirect:/listOuvrages";
        } catch (Exception e) {
            // Gérer l'erreur
            System.out.print(e);
            return "redirect:/listOuvrages";
        }
    }


}
