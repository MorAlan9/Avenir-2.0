package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.CausaRiesgo;
import com.avenir.Avenir20.service.CausaRiesgoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/causas-riesgo")
@CrossOrigin(origins = "*")
public class CausaRiesgoController {

    @Autowired
    private CausaRiesgoService service;

    @GetMapping
    @PreAuthorize("hasAuthority('VER_CAUSAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public List<CausaRiesgo> listar() {
        return service.listarTodas();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('VER_CAUSAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<CausaRiesgo> buscarPorId(@PathVariable Long id) {
        Optional<CausaRiesgo> causa = service.buscarPorId(id);
        return causa.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('CREAR_CAUSAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> crear(@RequestBody CausaRiesgo causa) {
        try {
            if (causa.getId() == null) causa.setActivo(true);
            CausaRiesgo nuevaCausa = service.guardar(causa);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCausa);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('EDITAR_CAUSAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody CausaRiesgo detalles) {
        Optional<CausaRiesgo> causaOpt = service.buscarPorId(id);
        if (causaOpt.isPresent()) {
            CausaRiesgo causa = causaOpt.get();
            causa.setNombre(detalles.getNombre());
            causa.setActivo(detalles.isActivo());
            try {
                return ResponseEntity.ok(service.guardar(causa));
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/baja")
    @PreAuthorize("hasAuthority('DAR_DE_BAJA_CAUSAS_RIESGO') or hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<CausaRiesgo> darDeBaja(@PathVariable Long id) {
        CausaRiesgo causa = service.darDeBaja(id);
        if (causa != null) return ResponseEntity.ok(causa);
        return ResponseEntity.notFound().build();
    }
}
