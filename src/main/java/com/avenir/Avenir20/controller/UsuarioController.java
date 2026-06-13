package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.model.UsuarioRequest;
import com.avenir.Avenir20.service.UsuarioService;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
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
        System.out.println("ClaveAcceso recibida: " + usuarioRequest.getClaveAcceso());
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(3, 1024, 1, usuarioRequest.getUsuario().getContrasena());
        usuarioRequest.getUsuario().setContrasena(hash);
        return service.guardar(usuarioRequest);
    }
}