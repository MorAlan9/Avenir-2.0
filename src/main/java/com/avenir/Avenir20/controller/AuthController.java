package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Login;
import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.service.UsuarioService;
import com.avenir.Avenir20.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios/login")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Login login){
        Usuario usuarioLogueado = service.obtenerUsuarioPorCredenciales(login);
        if(usuarioLogueado != null){
            String tokenJwt = jwtUtil.create(String.valueOf(usuarioLogueado.getIdUsuario()), usuarioLogueado.getEmail());
            Map<String, Object> response = new HashMap<>();
            response.put("token", tokenJwt);
            response.put("mensaje", "Login exitoso");
            return ResponseEntity.ok(response);
        }else{
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}