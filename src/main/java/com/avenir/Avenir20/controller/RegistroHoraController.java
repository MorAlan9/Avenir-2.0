package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.RegistroHora;
import com.avenir.Avenir20.service.RegistroHoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> registrarHora(@RequestBody Map<String, Object> payload) {
        try {
            Long idEmpresa = Long.valueOf(payload.get("idEmpresa").toString());
            String emailUsuario = payload.get("emailUsuario").toString(); // <--- Recibimos email
            LocalDate fecha = LocalDate.parse(payload.get("fecha").toString());
            Double horas = Double.valueOf(payload.get("horasDedicadas").toString());
            String tareas = payload.get("tareasRealizadas").toString();

            RegistroHora nuevoRegistro = service.guardarRegistro(idEmpresa, emailUsuario, fecha, horas, tareas);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoRegistro);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace(); // Esto te dirá el error exacto en la consola
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error procesando los datos.");
        }
    }

    // GET: Molde del calendario - Devuelve todos los usuarios que registraron horas en X fecha
    // Ejemplo de uso: /api/horas/calendario?fecha=2026-06-30
    @GetMapping("/calendario")
    public ResponseEntity<List<RegistroHora>> obtenerPorFecha(@RequestParam String fecha) {
        LocalDate fechaBuscada = LocalDate.parse(fecha);
        List<RegistroHora> registros = service.buscarPorFecha(fechaBuscada);
        return ResponseEntity.ok(registros);
    }
}