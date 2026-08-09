package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.ProbabilidadPrioridad;
import com.avenir.Avenir20.service.ProbabilidadPrioridadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/probabilidades-prioridad")
@CrossOrigin(origins = "*")
public class ProbabilidadPrioridadController {


    @Autowired
    private ProbabilidadPrioridadService service;

    @GetMapping
    @PreAuthorize("hasAuthority('VER_PROBABILIDADES_PRIORIDAD') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<ProbabilidadPrioridad> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_PROBABILIDADES_PRIORIDAD') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<ProbabilidadPrioridad> buscarPorId(@PathVariable Long id) {
        Optional<ProbabilidadPrioridad> registro = service.buscarPorId(id);
        return registro.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_PROBABILIDADES_PRIORIDAD') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody ProbabilidadPrioridad registro) {
        try {
            if (registro.getId() == null) registro.setActivo(true);
            ProbabilidadPrioridad nuevo = service.guardar(registro);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_PROBABILIDADES_PRIORIDAD') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody ProbabilidadPrioridad detalles) {
        Optional<ProbabilidadPrioridad> opt = service.buscarPorId(id);
        if (opt.isPresent()) {
            ProbabilidadPrioridad registro = opt.get();
            registro.setNombre(detalles.getNombre());
            registro.setActivo(detalles.isActivo());
            try {
                return ResponseEntity.ok(service.guardar(registro));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/baja")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_PROBABILIDADES_PRIORIDAD') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<ProbabilidadPrioridad> darDeBaja(@PathVariable Long id) {
        ProbabilidadPrioridad registro = service.darDeBaja(id);
        if (registro != null) return ResponseEntity.ok(registro);
        return ResponseEntity.notFound().build();
    }
}
