package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.TipoRiesgo;
import com.avenir.Avenir20.service.TipoRiesgoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipo-riesgo")
@CrossOrigin(origins = "*")
public class TipoRiesgoController {

    private final TipoRiesgoService service;

    public TipoRiesgoController(TipoRiesgoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TipoRiesgo>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<TipoRiesgo>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoRiesgo> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TipoRiesgo> crear(@RequestBody TipoRiesgo tipo) {
        return ResponseEntity.ok(service.guardar(tipo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoRiesgo> actualizar(@PathVariable Integer id, @RequestBody TipoRiesgo tipo) {
        return ResponseEntity.ok(service.actualizar(id, tipo));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}