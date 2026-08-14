package com.avenir.Avenir20.controller;

import com.avenir.Avenir20.model.Estado;
import com.avenir.Avenir20.service.EstadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estado")
@CrossOrigin(origins = "*")
public class EstadoController {

    private final EstadoService service;

    public EstadoController(EstadoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Estado>> listarTodos() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Estado>> listarActivos() {
        return ResponseEntity.ok(service.listarActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estado> obtenerPorId(@PathVariable Integer id) {
        return service.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Estado> crear(@RequestBody Estado estado) {
        return ResponseEntity.ok(service.guardar(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> actualizar(@PathVariable Integer id, @RequestBody Estado estado) {
        return ResponseEntity.ok(service.actualizar(id, estado));
    }

    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<Void> darDeBaja(@PathVariable Integer id) {
        service.darDeBaja(id);
        return ResponseEntity.noContent().build();
    }
}