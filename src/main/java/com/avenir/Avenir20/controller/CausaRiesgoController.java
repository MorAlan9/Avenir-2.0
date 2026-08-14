package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.CausaRiesgo;
import com.avenir.Avenir20.service.CausaRiesgoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/causa-riesgo")
@CrossOrigin(origins = "*")
public class CausaRiesgoController {

    private final CausaRiesgoService service;

    public CausaRiesgoController(CausaRiesgoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CausaRiesgo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<CausaRiesgo>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CausaRiesgo> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CausaRiesgo> crear(@RequestBody CausaRiesgo causa) {
        return ResponseEntity.ok(service.guardar(causa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CausaRiesgo> actualizar(@PathVariable Integer id, @RequestBody CausaRiesgo causa) {
        return ResponseEntity.ok(service.actualizar(id, causa));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}