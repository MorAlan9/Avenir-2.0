package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Permiso;
import com.avenir.Avenir20.model.TipoPersona;
import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.model.UsuarioRequest;
import com.avenir.Avenir20.repository.TipoPersonaRepository;
import com.avenir.Avenir20.service.UsuarioService;
import com.avenir.Avenir20.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Autowired
    private TipoPersonaRepository tipoPersonaRepository;

    @Autowired
    private JWTUtil jwtUtil;

    // GET: Listar todos los usuarios
    @GetMapping
    @PreAuthorize("hasAuthority('VER_USUARIOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<List<Usuario>> listar(@RequestParam(required = false) Boolean activo) {
        if (activo != null) {
            return ResponseEntity.ok(service.listarPorEstado(activo));
        }
        return ResponseEntity.ok(service.listarTodos());
    }

    // POST: Registro público (Soporta Admins con clave y Empleados sin clave)
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody UsuarioRequest request) {
        try {
            if (request == null || request.getUsuario() == null) {
                return ResponseEntity.badRequest().body("Los datos del usuario son obligatorios.");
            }

            Usuario usuario = request.getUsuario();
            String contrasenaPlana = usuario.getContrasena();

            if (contrasenaPlana == null || contrasenaPlana.length() < 6) {
                return ResponseEntity.badRequest().body("La contraseña debe tener al menos 6 caracteres.");
            }

            // Encriptación con Argon2
            Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
            String hash = argon2.hash(3, 1024, 1, contrasenaPlana);
            usuario.setContrasena(hash);

            // Validar clave máster de Administrador
            boolean esAdmin = request.getClaveAcceso() != null && "000010001".equals(request.getClaveAcceso().trim());

            String nombreRol;
            List<String> permisosNombres = new ArrayList<>();

            if (esAdmin) {
                // Es Admin: Nace ACTIVO y con Rol ADMINISTRADOR
                TipoPersona rolAdmin = tipoPersonaRepository.findByNombre("ADMINISTRADOR")
                        .orElseGet(() -> tipoPersonaRepository.findById(1L).orElse(null));

                usuario.setTipoPersona(rolAdmin);
                usuario.setActivo(true);
                nombreRol = "ADMINISTRADOR";

                if (rolAdmin != null && rolAdmin.getPermisos() != null) {
                    permisosNombres = rolAdmin.getPermisos().stream()
                            .map(Permiso::getNombre)
                            .collect(Collectors.toList());
                }
            } else {
                // 🛑 Es Estándar: Forzamos INACTIVO y creamos o buscamos un rol neutro "PENDIENTE"
                usuario.setActivo(false);

                TipoPersona rolPendiente = tipoPersonaRepository.findByNombre("PENDIENTE")
                        .orElseGet(() -> tipoPersonaRepository.findByNombre("SIN_ROL")
                                .orElseGet(() -> {
                                    // En vez de usar el ID 2 (que puede ser Gerente), creamos un rol neutro "PENDIENTE"
                                    TipoPersona nuevo = new TipoPersona();
                                    nuevo.setNombre("PENDIENTE");
                                    return tipoPersonaRepository.save(nuevo);
                                }));

                usuario.setTipoPersona(rolPendiente);
                nombreRol = "PENDIENTE";
            }

            // Guardar usuario en BD
            Usuario nuevoUsuario = service.guardar(usuario);

            // Generar Token JWT con el ROL y Permisos
            String tokenJwt = jwtUtil.createConPermisosYRol(
                    String.valueOf(nuevoUsuario.getIdUsuario()),
                    nuevoUsuario.getEmail(),
                    permisosNombres,
                    nombreRol
            );

            // Armar respuesta JSON
            Map<String, Object> response = new HashMap<>();
            response.put("usuario", nuevoUsuario);
            response.put("token", tokenJwt);
            response.put("rol", nombreRol);
            response.put("permisos", permisosNombres);
            response.put("mensaje", esAdmin
                    ? "Cuenta de Administrador registrada y activada con éxito."
                    : "Usuario registrado con éxito. Su cuenta está pendiente de aprobación.");

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al procesar el registro de usuario: " + e.getMessage());
        }
    }

    // PUT: Actualizar usuario (Aprobar, cambiar rol, editar datos)
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_USUARIOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        try {
            Usuario actualizado = service.actualizar(id, usuario);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DELETE: Dar de baja a un usuario
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_USUARIOS') or hasAuthority('ELIMINAR_USUARIOS') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> darDeBaja(@PathVariable Long id) {
        try {
            service.darDeBaja(id);
            return ResponseEntity.ok().body("{\"mensaje\": \"Usuario dado de baja exitosamente.\"}");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}