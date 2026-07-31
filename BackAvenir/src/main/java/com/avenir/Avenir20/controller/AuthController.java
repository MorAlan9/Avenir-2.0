package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Login;
import com.avenir.Avenir20.model.Permiso;
import com.avenir.Avenir20.model.Usuario;
import com.avenir.Avenir20.service.UsuarioService;
import com.avenir.Avenir20.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        if (usuarioLogueado != null) {
            // 🛑 BLOQUEO DE SEGURIDAD: Si no está activo, NO PUEDE ENTRAR
            if (usuarioLogueado.getActivo() == null || !usuarioLogueado.getActivo()) {
                Map<String, Object> response = new HashMap<>();
                response.put("mensaje", "Su cuenta está pendiente de aprobación por un administrador.");
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
            }

            // Identificar el rol
            String nombreRol = (usuarioLogueado.getTipoPersona() != null)
                    ? usuarioLogueado.getTipoPersona().getNombre().toUpperCase()
                    : "SIN_ROL";

            // Extraer permisos
            List<String> permisosNombres = new ArrayList<>();
            if (usuarioLogueado.getTipoPersona() != null && usuarioLogueado.getTipoPersona().getPermisos() != null) {
                permisosNombres = usuarioLogueado.getTipoPersona().getPermisos()
                        .stream()
                        .map(Permiso::getNombre)
                        .collect(Collectors.toList());
            }

            // Generar Token JWT
            String tokenJwt = jwtUtil.createConPermisosYRol(
                    String.valueOf(usuarioLogueado.getIdUsuario()),
                    usuarioLogueado.getEmail(),
                    permisosNombres,
                    nombreRol
            );

            Map<String, Object> response = new HashMap<>();
            response.put("token", tokenJwt);
            response.put("permisos", permisosNombres);
            response.put("username", usuarioLogueado.getEmail());
            response.put("rol", nombreRol);
            response.put("mensaje", "Login exitoso");

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("mensaje", "Credenciales inválidas");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }
}