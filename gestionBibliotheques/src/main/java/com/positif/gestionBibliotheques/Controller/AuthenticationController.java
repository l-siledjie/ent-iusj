package com.positif.gestionBibliotheques.Controller;

import com.positif.gestionBibliotheques.Controller.Api.AuthenticationApi;
import com.positif.gestionBibliotheques.Dto.auth.AuthenticationRequest;
import com.positif.gestionBibliotheques.Dto.auth.AuthenticationResponse;
import com.positif.gestionBibliotheques.Model.auth.ExtendedUser;
import com.positif.gestionBibliotheques.Services.auth.ApplicationUserDetailsService;
import com.positif.gestionBibliotheques.Utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthenticationController implements AuthenticationApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationUserDetailsService userDetailService;

    @Autowired
    private JwtUtil jwtUtil;

    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailService.loadUserByUsername(request.getLogin());

        final String jwt = jwtUtil.generateToken((ExtendedUser) userDetails);

        return ResponseEntity.ok(AuthenticationResponse.builder().accessToken(jwt).build());
    }

}
