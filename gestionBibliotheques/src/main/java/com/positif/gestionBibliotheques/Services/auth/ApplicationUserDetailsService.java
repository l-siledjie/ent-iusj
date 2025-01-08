package com.positif.gestionBibliotheques.Services.auth;

import com.positif.gestionBibliotheques.Dto.UtilisateurDto;
import com.positif.gestionBibliotheques.Model.auth.ExtendedUser;
import com.positif.gestionBibliotheques.Services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UtilisateurDto utilisateur = service.findByEmail(email);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(utilisateur.getRoles().getNom()));

        return new ExtendedUser(utilisateur.getEmail(), utilisateur.getPassWord(), authorities);


    }
}
