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
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // GET: Trae la lista de todos los usuarios (o filtra si se envía ?activo=true/false)
    @GetMapping
    public ResponseEntity<List<Usuario>> listar(@RequestParam(required = false) Boolean activo) {
        if (activo != null) {
            return ResponseEntity.ok(service.listarPorEstado(activo)); // Retorna filtrados
        }
        return ResponseEntity.ok(service.listarTodos()); // Retorna todos
    }

    // POST: Crea un usuario nuevo usando el envoltorio UsuarioRequest
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody UsuarioRequest request) {
        try {
            // 1. Validar la clave de acceso que manda el Front
            if (request.getClaveAcceso() == null || !request.getClaveAcceso().equals("000010001")) {
                return ResponseEntity.badRequest().body("Clave de acceso incorrecta o no proporcionada.");
            }

            // 2. Extraer el usuario limpio del envoltorio
            Usuario usuario = request.getUsuario();

            // 3. Validar longitud de la contraseña ANTES de encriptarla
            String contrasenaPlana = usuario.getContrasena();
            if (contrasenaPlana == null || contrasenaPlana.length() < 6) {
                return ResponseEntity.badRequest().body("La contraseña debe tener al menos 6 caracteres.");
            }

            // 4. Encriptar con Argon2
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(3, 1024, 1, contrasenaPlana);
            usuario.setContrasena(hash);

            // 5. Guardar usando el Service
            Usuario nuevoUsuario = service.guardar(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoUsuario);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // PUT: Modificar un usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = service.actualizar(id, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE: Dar de baja lógica a un usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<?> darDeBaja(@PathVariable Long id) {
        try {
            service.darDeBaja(id);
            return ResponseEntity.ok().body("{\"mensaje\": \"Usuario dado de baja exitosamente.\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}