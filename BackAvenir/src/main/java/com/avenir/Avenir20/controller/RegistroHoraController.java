package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.RegistroHora;
import com.avenir.Avenir20.service.RegistroHoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/horas")
@CrossOrigin(origins = "*")
public class RegistroHoraController {

    @Autowired
    private RegistroHoraService service;

    @PostMapping("/registrar")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> registrarHora(@RequestBody Map<String, Object> payload) {
        try {
            Long idEmpresa = Long.valueOf(payload.get("idEmpresa").toString());
            String emailUsuario = payload.get("emailUsuario").toString();
            LocalDate fecha = LocalDate.parse(payload.get("fecha").toString());
            Double horas = Double.valueOf(payload.get("horasDedicadas").toString());
            String tareas = payload.get("tareasRealizadas").toString();

            RegistroHora nuevoRegistro = service.guardarRegistro(idEmpresa, emailUsuario, fecha, horas, tareas);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando los datos.");
        }
    }

    @GetMapping("/calendario")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<RegistroHora>> obtenerPorFecha(@RequestParam String fecha) {
        LocalDate fechaBuscada = LocalDate.parse(fecha);
        List<RegistroHora> registros = service.buscarPorFecha(fechaBuscada);
        return ResponseEntity.ok(registros);
    }

    // 🌟 ENDPOINT PARA CAMBIAR EL ESTADO (APROBAR / RECHAZAR) VIA BODY JSON
    @PutMapping("/{id}/estado")
    @PreAuthorize("hasAuthority('APROBAR_HORARIOS') or hasAuthority('ROLE_ADMINISTRADOR') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        try {
            String nuevoEstado = body.get("estado");
            if (nuevoEstado == null || nuevoEstado.isEmpty()) {
                return ResponseEntity.badRequest().body("El estado es obligatorio.");
            }

            service.actualizarEstado(id, nuevoEstado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el estado de las horas.");
        }
    }

    // 🌟 ENDPOINT ALTERNATIVO VIA QUERY PARAM
    @PatchMapping("/{id}/estado")
    @PreAuthorize("hasAuthority('APROBAR_HORARIOS') or hasAuthority('ROLE_ADMINISTRADOR') or hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<?> cambiarEstadoPatch(@PathVariable Long id, @RequestParam String estado) {
        try {
            service.actualizarEstado(id, estado);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el estado.");
        }
    }
}