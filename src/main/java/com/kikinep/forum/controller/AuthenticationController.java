package com.kikinep.forum.controller;

import com.kikinep.forum.domain.user.User;
import com.kikinep.forum.domain.user.UserAuthenticationData;
import com.kikinep.forum.infra.security.JWTTokenData;
import com.kikinep.forum.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<JWTTokenData> authenticateUser(@RequestBody @Valid UserAuthenticationData data) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(data.username(), data.password()));
        String JWTToken = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new JWTTokenData(JWTToken));
    }
}
