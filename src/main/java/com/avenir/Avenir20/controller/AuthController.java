package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Login;
import com.avenir.Avenir20.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios/login")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UsuarioService service;

    @PostMapping
    public String login(@RequestBody Login login){
        return service.verificarCredenciales(login) ? "OK" : "FAIL";
    }
}
