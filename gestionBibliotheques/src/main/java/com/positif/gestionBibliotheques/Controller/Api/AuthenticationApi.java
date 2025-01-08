package com.positif.gestionBibliotheques.Controller.Api;

import com.positif.gestionBibliotheques.Dto.auth.AuthenticationRequest;
import com.positif.gestionBibliotheques.Dto.auth.AuthenticationResponse;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.positif.gestionBibliotheques.Utils.Constantes.routeAuth;

@Api("authentication")
public interface AuthenticationApi {

    @PostMapping(value = routeAuth + "/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request);

}
