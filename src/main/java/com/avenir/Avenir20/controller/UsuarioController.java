package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Usuario;
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

    // POST: Crea un usuario nuevo con validaciones y Argon2
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Usuario usuario) {
        try {
            // 1. Validar longitud de la contraseña ANTES de encriptarla
            String contrasenaPlana = usuario.getContrasena();
            if (contrasenaPlana == null || contrasenaPlana.length() < 6) {
                return ResponseEntity.badRequest().body("La contraseña debe tener al menos 6 caracteres.");
            }

            // 2. Encriptar con Argon2
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(3, 1024, 1, contrasenaPlana);
            usuario.setContrasena(hash);

            // 3. Guardar usando el Service
            Usuario nuevoUsuario = service.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (IllegalArgumentException e) {
            // Atrapa errores del Service (como email repetido o inválido)
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}