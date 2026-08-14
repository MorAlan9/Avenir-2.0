package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.TipoRiesgo;
import com.avenir.Avenir20.service.TipoRiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tipos-riesgo")
@CrossOrigin(origins = "*")
public class TipoRiesgoController {

    @Autowired
    private TipoRiesgoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('VER_TIPOS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<TipoRiesgo> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_TIPOS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<TipoRiesgo> buscarPorId(@PathVariable Long id) {
        Optional<TipoRiesgo> tipo = service.buscarPorId(id);
        return tipo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_TIPOS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody TipoRiesgo tipo) {
        try {
            if (tipo.getId() == null) tipo.setActivo(true);
            TipoRiesgo nuevoTipo = service.guardar(tipo);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoTipo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_TIPOS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody TipoRiesgo detalles) {
        Optional<TipoRiesgo> tipoOpt = service.buscarPorId(id);
        if (tipoOpt.isPresent()) {
            TipoRiesgo tipo = tipoOpt.get();
            tipo.setNombre(detalles.getNombre());
            tipo.setActivo(detalles.isActivo());
            try {
                return ResponseEntity.ok(service.guardar(tipo));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/baja")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_TIPOS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<TipoRiesgo> darDeBaja(@PathVariable Long id) {
        TipoRiesgo tipo = service.darDeBaja(id);
        if (tipo != null) return ResponseEntity.ok(tipo);
        return ResponseEntity.notFound().build();
    }
}
