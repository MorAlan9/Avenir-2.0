package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.model.UsuarioRequest;
import com.avenir.Avenir20.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // GET: Trae la lista de todos los usuarios
    @GetMapping
    public List<Usuario> listar() {
        return service.listarTodos();
    }

    // POST: Crea un usuario nuevo (UH-3)

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody UsuarioRequest usuarioRequest) {
        return service.guardar(usuarioRequest);
    }
}