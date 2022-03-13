package com.conexa.saude.api.desafiotecnicobackendconexa.gateway.controller;

import com.conexa.saude.api.desafiotecnicobackendconexa.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/logoff")
    public void logoff(@RequestHeader("Authentication") String token) {
        jwtUtil.logout(token.substring(7));
    }
}
